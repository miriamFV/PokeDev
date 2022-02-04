package com.example.data.repository

import PokemonModel
import PokemonSpeciesModel
import Types
import com.example.data.database.AppDatabase
import com.example.data.database.entities.*
import com.example.data.model.detail.PokemonDetailModel
import com.example.data.model.detail.toPokemonDetailModel
import com.example.data.remote.PokemonAPI
import com.example.data.remote.PokemonSpeciesAPI
import com.example.data.remote.ResultHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(
    private val pokemonAPI: PokemonAPI,
    private val pokemonSpeciesAPI: PokemonSpeciesAPI,
    private val db: AppDatabase
) {

    suspend fun getPokemon(idPokemon: Int): PokemonModel {
        return withContext(Dispatchers.IO){
            pokemonAPI.getPokemon(idPokemon.toString())
        }
    }

    suspend fun getPokemonSpecies(idPokemon: Int): PokemonSpeciesModel {
        return withContext(Dispatchers.IO){ pokemonSpeciesAPI.getPokemonSpecies(idPokemon.toString()) }
    }

    suspend fun getPokemonList(limInf: Int, limSup: Int): MutableList<PokemonDetailModel> {

        //Collect data from two endpoints and parse them to the data model we need
        val pokemonDetailList : MutableList<PokemonDetailModel> = arrayListOf()

        for (idPokemon in limInf..limSup) {
            val pokemon : PokemonModel = getPokemon(idPokemon)
            val pokemonSpecies : PokemonSpeciesModel = getPokemonSpecies(idPokemon)

            val pokemonDetail = PokemonDetailModel(
                id = pokemon.id,
                name = pokemon.name,
                weight = pokemon.weight,
                height = pokemon.height,
                sprites = pokemon.sprites,
                specie = getPokemonSpecie(pokemonSpecies),
                types = pokemon.types,
                description = getPokemonDescription(pokemonSpecies)
            )
            pokemonDetailList.add(pokemonDetail)

            //Save data locally (database)
            savePokemonLocally(pokemonDetail)
        }
        return pokemonDetailList
    }


    private fun getTypes(pokemon: PokemonModel): List<String> {
        val types : MutableList<String> = mutableListOf()
        pokemon.types.forEach {
            types.add(it.type.name)
        }
        return types
    }

    private fun getPokemonDescription(pokemonSpecies: PokemonSpeciesModel): String {
        var description  = ""
        pokemonSpecies.flavor_text_entries.forEach {
            if(it.language.name == "es" && it.version.name == "alpha-sapphire"){
                description = it.flavor_text
            }
        }
        return description
    }

    private fun getPokemonSpecie(pokemonSpecies: PokemonSpeciesModel): String {
        var specie = ""
        pokemonSpecies.genera.forEach {
            if (it.language.name == "es"){ specie = it.genus }
        }
        return specie
    }



    //--------- DATABASE --------- DATABASE --------- DATABASE --------- DATABASE --------- DATABASE ---------

    //PokemonDetailEntity
    suspend fun savePokemonDatabase(vararg pokemonDetailEntity: PokemonDetailEntity){
        withContext(Dispatchers.IO){ db.pokemonDao().savePokemonDetail(*pokemonDetailEntity)}
    }

    suspend fun getPokemonListDatabase():List<PokemonDetailEntity>{
        return withContext(Dispatchers.IO){ db.pokemonDao().getPokemonListDetail() }
    }

    suspend fun getPokemonFavoriteListDatabase():List<PokemonDetailEntity>{
        return withContext(Dispatchers.IO){ db.pokemonDao().getPokemonFavoriteListDetail() }
    }

    suspend fun getPokemonDatabase(id: Int):PokemonDetailEntity{
        return withContext(Dispatchers.IO){ db.pokemonDao().getPokemonDetail(id) }
    }

    suspend fun deletePokemonDatabase(){
        withContext(Dispatchers.IO){
            db.pokemonDao().deletePokemonDetail()
        }
    }

    //SpritesEntity
    suspend fun saveSpritesDatabase(vararg spritesEntity: SpritesEntity){
        db.pokemonDao().savePokemonSprites(*spritesEntity)
    }

    suspend fun getSpritesDatabase(id: Int):SpritesEntity{
        return withContext(Dispatchers.IO){ db.pokemonDao().getPokemonSprites(id) }
    }

    //TypesEntitiy
    suspend fun saveTypesDatabase(vararg typesEntity: TypesEntity){
        withContext(Dispatchers.IO){ db.pokemonDao().savePokemonTypes(*typesEntity) }
    }

    suspend fun getPokemonTypes(idPokemon: Int):List<TypesEntity>{
        return withContext(Dispatchers.IO){
            db.pokemonDao().getPokemonTypes(idPokemon)
        }
    }

    suspend fun getPokemonType(idPokemon: Int, slot: Int):TypesEntity{
        return withContext(Dispatchers.IO){
            db.pokemonDao().getPokemonType(idPokemon, slot)
        }
    }

    suspend fun getTypesDatabase(id: Int): List<TypesEntity>{
        var pokemonTypesEntityList : MutableList<TypesEntity> = arrayListOf()
        getPokemonTypes(id).forEach {
            pokemonTypesEntityList.add(getPokemonType(id, it.slot))
        }
        return pokemonTypesEntityList
    }

    suspend fun getTypeDatabase(id: Int, slot: Int): TypesEntity{
        return withContext(Dispatchers.IO){ db.pokemonDao().getPokemonType(id, slot)}
    }

    //Generic

    suspend fun savePokemonLocally(pokemonDetailModel : PokemonDetailModel): ResultHandler<String> {
        return withContext(Dispatchers.IO){

            //Save pokemon Sprites data in database
            val spritesEntity : SpritesEntity = pokemonDetailModel.sprites.toSpritesEntity(pokemonDetailModel.id)
            saveSpritesDatabase(spritesEntity)

            //save pokemonDetail data in database
            val pokemonDetailEntity : PokemonDetailEntity = pokemonDetailModel.toPokemonDetailEntity()
            savePokemonDatabase(pokemonDetailEntity)

            //Save pokemon types data in database
            val pokemonTypes : List<Types> = pokemonDetailModel.types
            val pokemonTypesEntityList : MutableList<TypesEntity> = arrayListOf()
            pokemonTypes.forEach {
                val typeEntity : TypesEntity = it.toTypesEntity(pokemonDetailModel.id)
                pokemonTypesEntityList.add(typeEntity)
            }
            //saveTypesDatabase(typeEntity)
            saveTypesDatabase(*pokemonTypesEntityList.toTypedArray())

            ResultHandler.Success("Success")
        }
    }

    suspend fun getPokemonListLocally(): MutableList<PokemonDetailModel> {

        val pokemonDetailModelList : MutableList<PokemonDetailModel> = arrayListOf()
        val pokemonDetailEntityList : List<PokemonDetailEntity> = getPokemonListDatabase()

        pokemonDetailEntityList.forEach { pokemonDetailEntity ->
            val pokemonSpritesEntity : SpritesEntity = getSpritesDatabase(pokemonDetailEntity.id)
            val pokemonTypesEntity : List<TypesEntity> = getTypesDatabase(pokemonDetailEntity.id)
            val pokemonDetailModel: PokemonDetailModel = pokemonDetailEntity.toPokemonDetailModel(pokemonSpritesEntity,pokemonTypesEntity)
            pokemonDetailModelList.add(pokemonDetailModel)
        }
        return pokemonDetailModelList
    }

    suspend fun getPokemonLocally(id: Int): PokemonDetailModel {
        val pokemonDetailEntity: PokemonDetailEntity = getPokemonDatabase(id)
        val pokemonSpritesEntity: SpritesEntity = getSpritesDatabase(id)
        val pokemonTypesEntity: List<TypesEntity> = getTypesDatabase(id)
        return pokemonDetailEntity.toPokemonDetailModel(pokemonSpritesEntity, pokemonTypesEntity)
    }

    suspend fun getPokemonFavoriteListLocally(): MutableList<PokemonDetailModel> {

        val pokemonDetailModelList : MutableList<PokemonDetailModel> = arrayListOf()
        val pokemonDetailEntityList : List<PokemonDetailEntity> = getPokemonFavoriteListDatabase()

        pokemonDetailEntityList.forEach { pokemonDetailEntity ->
            val pokemonSpritesEntity : SpritesEntity = getSpritesDatabase(pokemonDetailEntity.id)
            val pokemonTypesEntity : List<TypesEntity> = getTypesDatabase(pokemonDetailEntity.id)
            val pokemonDetailModel: PokemonDetailModel = pokemonDetailEntity.toPokemonDetailModel(pokemonSpritesEntity,pokemonTypesEntity)
            pokemonDetailModelList.add(pokemonDetailModel)
        }
        return pokemonDetailModelList
    }

}
package com.example.pokedev.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.detail.PokemonDetailModel
import com.example.pokedev.R
import com.example.pokedev.common.getTypeBackground
import com.example.pokedev.databinding.ItemFavoriteBinding
import com.squareup.picasso.Picasso
import java.util.*

class FavoriteAdapter(
    pokemonList: List<PokemonDetailModel> = emptyList(),
    private val listener:(PokemonDetailModel) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val mutablePokemonList : MutableList<PokemonDetailModel> = mutableListOf(*pokemonList.toTypedArray())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(mutablePokemonList[position])
    }

    override fun getItemCount(): Int {
        return mutablePokemonList.size
    }

    fun updateList(list: List<PokemonDetailModel>){
        mutablePokemonList.clear()
        mutablePokemonList.addAll(list)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val binding : ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root){

        val context = itemView.context

        fun bind(pokemondetailModel: PokemonDetailModel){
            //listener
            itemView.setOnClickListener {
                listener.invoke(pokemondetailModel)
            }

            binding.apply {
                tvItemFavoriteId.text = String.format(context.getString(R.string.pokemon_id),pokemondetailModel.id)
                tvItemFavoriteName.text = pokemondetailModel.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }

                ibItemFavoriteLike.setImageResource(R.drawable.ic_like_medium)
                tvItemFavoriteWeight.text = pokemondetailModel.weight.toString()
                tvItemFavoriteSpecies.text = pokemondetailModel.specie
                Picasso.get().load(pokemondetailModel.sprites.front_default).into(ivItemFavoritePokemon)
                clItemFavorite.background = pokemondetailModel.getTypeBackground(context)//background color
            }
        }
    }

}
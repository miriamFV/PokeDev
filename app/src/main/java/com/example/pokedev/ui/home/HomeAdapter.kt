package com.example.pokedev.ui.home

import PokemonModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.detail.PokemonDetailModel
import com.example.pokedev.R
import com.example.pokedev.common.getColor
import com.example.pokedev.databinding.ItemHomeBinding
import com.squareup.picasso.Picasso
import java.util.*

class HomeAdapter(
    pokemonList: List<PokemonDetailModel> = emptyList(),
    private val listener:(PokemonDetailModel) -> Unit
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val mutablePokemonList : MutableList<PokemonDetailModel> = mutableListOf(*pokemonList.toTypedArray())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context))
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
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

    inner class HomeViewHolder(private val binding : ItemHomeBinding) : RecyclerView.ViewHolder(binding.root){

        val context = itemView.context

        fun bind(pokemondetailModel:PokemonDetailModel){
            //listener
            itemView.setOnClickListener {
                listener.invoke(pokemondetailModel)
            }

            binding.apply {
                tvItemHomeId.text = String.format(context.getString(R.string.pokemon_id),pokemondetailModel.id)
                tvItemHomeName.text = pokemondetailModel.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                tvItemHomeWeight.text = pokemondetailModel.weight.toString()
                tvItemHomeSpecies.text = pokemondetailModel.specie
                Picasso.get().load(pokemondetailModel.sprites.front_default).into(ivItemHomePokemon)
                cvItemHome.setCardBackgroundColor(pokemondetailModel.getColor(context)) //background color
            }
        }
    }


}
package com.example.pokedev.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.example.data.model.detail.PokemonDetailModel
import com.example.data.utils.PokemonUtils
import com.example.pokedev.R
import com.example.pokedev.common.*
import com.example.pokedev.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso




class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {


    override val vm: DetailViewModel by sharedViewModel()
    private val args: DetailFragmentArgs by navArgs()
    private val pokemonDetail by lazy { args.pokemonDetail }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.onInit(pokemonDetail.id)
        setupListener()
        observeData(vm.obsPokemonDetail,::onObservePokemonDetail)
    }

    private fun onObservePokemonDetail(pokemon: PokemonDetailModel) {
        binding.apply {
            Picasso.get().load(pokemon.sprites.front_default).into(ivFragmentDetailPokemon) //Image
            tvFragmentDetailId.text = R.string.pokemon_id.getString(requireContext(),pokemon.id) //Id
            tvFragmentDetailName.text = pokemon.name.capitalize() //Name
            setImageLikeButton(pokemon.favorite) //Like button
            tvFragmentDetailSpecies.text = R.string.pokemon_specie.getString(requireContext(),pokemon.specie)//Specie
            tvFragmentDetailTypes.text = R.string.pokemon_type.getString(requireContext(),PokemonUtils.getTypes(pokemon)) //Types
            tvFragmentDetailWeight.text = R.string.pokemon_weight.getString(requireContext(),PokemonUtils.convertWeight(pokemon.weight)) //Weight
            tvFragmentDetailHeight.text = R.string.pokemon_height.getString(requireContext(),PokemonUtils.convertHeight(pokemon.height)) //Height
            tvFragmentDetailDescriptionBody.text = pokemon.description //Description
            //clFragmentDetail.setBackgroundColor(pokemon.getColor(requireContext())) //Background color
            clFragmentDetail.background = pokemon.getTypeBackground(requireContext())
        }
    }

    private fun setupListener() {
        binding.ibLike.setOnClickListener {
            vm.onActionLikeClicked()
        }
    }

    private fun setImageLikeButton(like:Boolean){
        binding.apply {
            if(like){
                ibLike.setImageResource(R.drawable.ic_like_big)
            }else{
                ibLike.setImageResource(R.drawable.ic_dislike_big)
            }
        }
    }


}
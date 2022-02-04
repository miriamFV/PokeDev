package com.example.pokedev.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.detail.PokemonDetailModel
import com.example.pokedev.common.BaseFragment
import com.example.pokedev.common.NavData
import com.example.pokedev.databinding.FragmentFavoriteBinding
import com.example.pokedev.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override val vm: FavoriteViewModel by viewModel()

    private val favoriteAdapter by lazy {
        FavoriteAdapter(){
            vm.onActionPokemonClicked(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        vm.onInit()
        setUpBinding()
        setUpObserver()
    }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    private fun setUpRecycler(){
        binding.apply{
            rvFavorite.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvFavorite.adapter = favoriteAdapter

            val deleteHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    vm.onActionOnItemSwiped(viewHolder.adapterPosition)
                }
            }

            ItemTouchHelper(deleteHelper).attachToRecyclerView(rvFavorite)
        }
    }

    private fun setUpBinding(){
        vm.obsPokemonList.observe(viewLifecycleOwner){
            favoriteAdapter.updateList(it)
        }
    }

    private fun setUpObserver(){
        observeData(vm.obsNoFavoritesToShow,::onObserveNoFavoritesToShow)
    }

    private fun onObserveNoFavoritesToShow(emptyDatabase : Boolean) {
        if (emptyDatabase){
            binding.rvFavorite.visibility = View.GONE
            binding.tvNoFavorites.visibility = View.VISIBLE
        }else{
            binding.rvFavorite.visibility = View.VISIBLE
            binding.tvNoFavorites.visibility = View.GONE
        }
    }

    override fun onNavigate(navData: NavData) {
        when(navData.id){
            HomeViewModel.NAV_DETAIL ->{
                val pokemon = navData.data as PokemonDetailModel
                findNavController().navigate(FavoriteFragmentDirections.actionNavigationFavoritesToDetailFragment(pokemon))
            }
        }
    }
}
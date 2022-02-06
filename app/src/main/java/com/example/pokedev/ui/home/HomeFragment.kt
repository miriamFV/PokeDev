package com.example.pokedev.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.model.detail.PokemonDetailModel
import com.example.pokedev.common.BaseFragment
import androidx.navigation.fragment.findNavController
import com.example.pokedev.common.NavData
import com.example.pokedev.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val vm: HomeViewModel by sharedViewModel()

    private val homeAdapter by lazy {
        HomeAdapter(){
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

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    private fun setUpRecycler(){
        binding.apply{
            rvHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvHome.adapter = homeAdapter
        }
    }

    private fun setUpBinding(){
        vm.obsPokemonList.observe(viewLifecycleOwner){
            homeAdapter.updateList(it)
        }
    }

    private fun setUpObserver(){
        observeData(vm.obsNoDataToShow,::onObserveNoDataToShow)
    }

    private fun onObserveNoDataToShow(emptyDatabase : Boolean) {
        binding.apply {
            if (emptyDatabase){
                rvHome.visibility = View.GONE
                tvNoConnection.visibility = View.VISIBLE
            }else{
                rvHome.visibility = View.VISIBLE
                tvNoConnection.visibility = View.GONE
            }
        }
    }

    override fun onNavigate(navData: NavData) {
        when(navData.id){
            HomeViewModel.NAV_DETAIL ->{
                val pokemon = navData.data as PokemonDetailModel
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToDetailFragment(pokemon))
            }
        }
    }
}
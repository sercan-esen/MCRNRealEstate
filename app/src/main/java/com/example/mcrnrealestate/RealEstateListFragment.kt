package com.example.mcrnrealestate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mcrnrealestate.databinding.FragmentRealEstateListBinding

class RealEstateListFragment : Fragment() {
    private lateinit var binding: FragmentRealEstateListBinding
    private lateinit var realEstateAdapter: RealEstateRV
    private val viewModel: RealEstateListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRealEstateListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeRealEstates()
        observeLoader()
        initOnItemSelectedListener()
    }

    private fun initAdapter() {
        realEstateAdapter = RealEstateRV(requireContext())
        binding.rvRealEstate.apply {
            adapter = realEstateAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun observeRealEstates() {
        viewModel.realEstateList.observe(viewLifecycleOwner, Observer{
            if(it != null) {
                realEstateAdapter.updateList(it)
            }
        })
    }

    private fun observeLoader() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer{
            if(it)
                binding.pbList.visibility = View.VISIBLE
            else
                binding.pbList.visibility = View.INVISIBLE
        })
    }
    private fun initOnItemSelectedListener(){
        realEstateAdapter.onItemSelected { selectedItem ->
            val bundle = Bundle()
            bundle.putParcelable(REAL_ESTATE_DETAIL, selectedItem)
            findNavController().navigate(R.id.action_realEstateListFragment_to_realEstateDetailFragment, bundle)
        }
    }

    companion object{
        const val REAL_ESTATE_DETAIL = "REAL_ESTATE_DETAIL"
    }
}
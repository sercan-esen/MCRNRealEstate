package com.example.mcrnrealestate

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.mcrnrealestate.RealEstateListFragment.Companion.REAL_ESTATE_DETAIL
import com.example.mcrnrealestate.databinding.FragmentRealEstateDetailBinding
import com.google.android.material.snackbar.Snackbar

class RealEstateDetailFragment: Fragment() {
    private lateinit var binding: FragmentRealEstateDetailBinding
    private var realEstateDetail: DataItem? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        binding.apply {
            ivRealEstateDetail.loadWithGlide(realEstateDetail!!.img_src)
            tvIdDetail.text = realEstateDetail!!.id
            tvRealEstatePriceDetail.text = String.format(requireContext()
                .getString(R.string.real_estate_price), realEstateDetail!!.price)
            tvPropertyTypeDetail.text = String.format(requireContext().getString(R.string.real_estate_type), realEstateDetail!!.type)
            ivDetailBack.setOnClickListener {
                findNavController().popBackStack()
            }
            detailBuyNowButton.setOnClickListener {
                Snackbar.make(it,"Congratulations, you got this.",Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun getArgs() {
        if (arguments?.isEmpty != true) {
            arguments?.let {
              realEstateDetail = it.getParcelable<DataItem>(REAL_ESTATE_DETAIL)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRealEstateDetailBinding.inflate(layoutInflater)
        return binding.root
    }


        private fun ImageView.loadWithGlide(imageUrl: String) {
            Glide
                .with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_view_vector)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(this)
        }
    }

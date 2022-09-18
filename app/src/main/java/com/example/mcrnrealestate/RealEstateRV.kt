package com.example.mcrnrealestate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mcrnrealestate.databinding.RecyclerRowBinding

class RealEstateRV constructor(private val context: Context) :
    RecyclerView.Adapter<RealEstateRV.ViewHolder>() {
    private var realEstateList: List<DataItem> = emptyList()
    private lateinit var listener: (DataItem) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val realEstate = realEstateList.get(position)

        holder.rootView.apply {
            tvRealEstatePrice.text = String.format(context.resources.getString(R.string.real_estate_price), realEstate.price)
            tvId.text = realEstate.id
            ivRealEstate.loadWithGlide(realEstate.img_src)
            tvPropertyType.text = String.format(context.resources.getString(R.string.real_estate_type), realEstate.type)
            ivRealEstate.setOnClickListener {
                listener.invoke(realEstate)
            }
        }

    }

    override fun getItemCount(): Int = realEstateList.size


    inner class ViewHolder(view: RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {
        val rootView = view
    }

    fun updateList(list: List<DataItem>) {
        realEstateList = list
        notifyDataSetChanged()
    }

    private fun ImageView.loadWithGlide(imageUrl: String) {
        Glide
            .with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_view_vector)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(this)
    }
    fun onItemSelected (callback: (DataItem) -> Unit){
        listener = callback
    }
}
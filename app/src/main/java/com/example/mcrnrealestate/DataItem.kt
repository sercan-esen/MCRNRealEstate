package com.example.mcrnrealestate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataItem(
    val id: String,
    val img_src: String,
    val price: Int,
    val type: String
) : Parcelable
package com.example.mcrnrealestate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class RealEstateListViewModel: ViewModel() {

    val realEstateList = MutableLiveData<List<DataItem>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        getRealEstates()
    }

    private fun getRealEstates() {
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val realEstates = RetrofitProvider.retrofit.getRealEstates()
                isLoading.postValue(false)
                realEstateList.postValue(realEstates)
            } catch (e: Exception) {
                isLoading.postValue(false)
                e.printStackTrace()
            }
        }

    }
}
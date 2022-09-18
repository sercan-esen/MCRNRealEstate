package com.example.mcrnrealestate


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://mars.udacity.com/"
const val TIME_OUT = 15L

object RetrofitProvider {

    val retrofit: ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(provideLoggingInceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    private fun provideLoggingInceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLoggingInterceptor)
        httpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        return httpClient.build()
    }
}
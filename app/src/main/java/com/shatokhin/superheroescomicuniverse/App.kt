package com.shatokhin.superheroescomicuniverse

import android.app.Application
import com.google.gson.Gson
import com.shatokhin.superheroescomicuniverse.data.network.APISuperHeroes
import com.shatokhin.superheroescomicuniverse.utilites.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var apiSuperHeroes: APISuperHeroes

    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
    }

    private fun initRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        apiSuperHeroes = retrofit.create(APISuperHeroes::class.java)

    }
}
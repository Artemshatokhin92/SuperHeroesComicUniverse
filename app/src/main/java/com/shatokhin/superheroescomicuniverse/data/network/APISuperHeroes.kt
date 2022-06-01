package com.shatokhin.superheroescomicuniverse.data.network

import com.shatokhin.superheroescomicuniverse.data.models.SuperHeroJson
import com.shatokhin.superheroescomicuniverse.utilites.TOKEN
import retrofit2.http.GET
import retrofit2.http.Path

interface APISuperHeroes {

    @GET("$TOKEN/{id}")
    suspend fun getSuperHeroesById(@Path("id") idHero: Int): SuperHeroJson

}
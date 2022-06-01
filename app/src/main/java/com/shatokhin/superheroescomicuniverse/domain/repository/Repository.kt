package com.shatokhin.superheroescomicuniverse.domain.repository

import com.shatokhin.superheroescomicuniverse.data.models.SuperHeroJson

interface Repository {

    suspend fun getSuperHeroById(idHero: Int): SuperHeroJson

}
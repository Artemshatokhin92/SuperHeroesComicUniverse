package com.shatokhin.superheroescomicuniverse.data.repository

import com.shatokhin.superheroescomicuniverse.App
import com.shatokhin.superheroescomicuniverse.data.models.SuperHeroJson
import com.shatokhin.superheroescomicuniverse.data.network.APISuperHeroes
import com.shatokhin.superheroescomicuniverse.domain.repository.Repository

class RepositoryImpl: Repository {
    private val apiSuperHeroes = App.instance.apiSuperHeroes

    override suspend fun getSuperHeroById(idHero: Int): SuperHeroJson {
        return apiSuperHeroes.getSuperHeroesById(idHero)
    }

}
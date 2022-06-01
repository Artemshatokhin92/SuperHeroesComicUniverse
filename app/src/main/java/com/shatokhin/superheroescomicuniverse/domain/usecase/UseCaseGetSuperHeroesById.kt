package com.shatokhin.superheroescomicuniverse.domain.usecase

import com.shatokhin.superheroescomicuniverse.data.models.SuperHeroJson
import com.shatokhin.superheroescomicuniverse.domain.repository.Repository

class UseCaseGetSuperHeroesById(private val repository: Repository) {
    suspend fun execute(idHero: Int): SuperHeroJson {
        return repository.getSuperHeroById(idHero)
    }
}
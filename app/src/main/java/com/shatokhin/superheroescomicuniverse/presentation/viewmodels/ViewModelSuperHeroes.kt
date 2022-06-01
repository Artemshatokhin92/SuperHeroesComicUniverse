package com.shatokhin.superheroescomicuniverse.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shatokhin.superheroescomicuniverse.data.models.SuperHeroJson
import com.shatokhin.superheroescomicuniverse.domain.usecase.UseCaseGetSuperHeroesById
import com.shatokhin.superheroescomicuniverse.utilites.TAG_FOR_LOGCAT
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ViewModelSuperHeroes(private val useCaseGetSuperHeroesById: UseCaseGetSuperHeroesById) :
    ViewModel() {

    private val mListSuperHeroJson = MutableLiveData<List<SuperHeroJson>>()
    val listSuperHeroJson: LiveData<List<SuperHeroJson>>
        get() = mListSuperHeroJson

    private suspend fun loadHeroesById(idHero: Int): SuperHeroJson? {
        try {
            return useCaseGetSuperHeroesById.execute(idHero)
        } catch (http: HttpException) {
            val error = "Error network: ${http.code()}"
            Log.d(TAG_FOR_LOGCAT, error)
        } catch (e: Exception) {
            val error = "Hero not received, server error"
            Log.d(TAG_FOR_LOGCAT, error)
        }
        return null
    }

    fun loadRandomHeroes(countHeroes: Int) {
        viewModelScope.launch {
            var count = countHeroes
            val listHeroes = mutableListOf<SuperHeroJson>()

            while (count > 0) {
                val randomIdHero = (1..731).random()
                val hero = loadHeroesById(randomIdHero)
                hero?.let {
                    listHeroes.add(it)
                }
                count--
            }

            mListSuperHeroJson.value = listHeroes
        }
    }
}
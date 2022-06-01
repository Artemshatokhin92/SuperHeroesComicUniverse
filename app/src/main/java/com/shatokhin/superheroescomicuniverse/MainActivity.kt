package com.shatokhin.superheroescomicuniverse

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.shatokhin.superheroescomicuniverse.data.models.SuperHeroJson
import com.shatokhin.superheroescomicuniverse.databinding.ActivityMainBinding
import com.shatokhin.superheroescomicuniverse.domain.models.PowerStatsHeroParcelable
import com.shatokhin.superheroescomicuniverse.presentation.adapters.AdapterRvSuperHeroes
import com.shatokhin.superheroescomicuniverse.presentation.viewmodels.ViewModelSuperHeroes
import com.shatokhin.superheroescomicuniverse.presentation.viewmodels.ViewModelSuperHeroesFactory
import com.shatokhin.superheroescomicuniverse.utilites.EXTRA_HERO_PARCELABLE

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModelListAllHeroes: ViewModelSuperHeroes by viewModels { ViewModelSuperHeroesFactory() }
    lateinit var adapterRvSuperHeroes: AdapterRvSuperHeroes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()

        binding.btnLoadRandomHeroes.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModelListAllHeroes.loadRandomHeroes(20)
        }

    }

    private fun initRecycleView() {
        val clickListener = object : AdapterRvSuperHeroes.ClickListenerSuperHero{
            override fun clickHero(hero: SuperHeroJson) {
                val intent = Intent(this@MainActivity, DetailsHeroActivity::class.java)
                val powerStatsHeroParcelable = PowerStatsHeroParcelable(
                    hero.name,
                    hero.image.url,
                    hero.powerStatsJson.intelligence,
                    hero.powerStatsJson.strength,
                    hero.powerStatsJson.speed,
                    hero.powerStatsJson.durability,
                    hero.powerStatsJson.power,
                    hero.powerStatsJson.combat
                )
                intent.putParcelableArrayListExtra(EXTRA_HERO_PARCELABLE, arrayListOf(powerStatsHeroParcelable))
                startActivity(intent)
            }
        }

        adapterRvSuperHeroes = AdapterRvSuperHeroes(clickListener)
        binding.rvHeroes.adapter = adapterRvSuperHeroes

        val lmForRvHeroes = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.rvHeroes.layoutManager = lmForRvHeroes

        viewModelListAllHeroes.listSuperHeroJson.observe(this){ listRandomSuperHeroes ->
            binding.progressBar.visibility = View.GONE
            adapterRvSuperHeroes.submitList(listRandomSuperHeroes)
        }

    }

}
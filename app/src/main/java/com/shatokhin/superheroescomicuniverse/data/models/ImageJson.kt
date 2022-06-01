package com.shatokhin.superheroescomicuniverse.data.models

import com.google.gson.annotations.SerializedName

data class ImageJson(
    @SerializedName("url")
    val url: String,
)
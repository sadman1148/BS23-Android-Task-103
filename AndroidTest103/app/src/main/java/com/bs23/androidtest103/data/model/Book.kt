package com.bs23.androidtest103.data.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("url")
    val url: String
)

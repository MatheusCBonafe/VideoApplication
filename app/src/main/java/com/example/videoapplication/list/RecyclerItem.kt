package com.example.videoapplication.list

import java.io.Serializable

data class RecyclerItem(
    val id : Long,
    val imageResource: String,
    val text1: String,
    val text2: String,
    val releaseDate: String,
    val hasFavorite: Boolean
) : Serializable


package com.example.moviedbapp.moviedetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDataModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val movieId: String,

    val title: String
)
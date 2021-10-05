package com.example.moviedbapp.favoritelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedbapp.database.MovieDataRepository
import java.lang.IllegalArgumentException

class FavoriteListViewModelFactory(private val repository: MovieDataRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return try {
            val constructor = modelClass.getDeclaredConstructor(MovieDataRepository::class.java)
            constructor.newInstance(repository)
        } catch (t:Throwable) {
            throw IllegalArgumentException("moooo")
        }
    }
}
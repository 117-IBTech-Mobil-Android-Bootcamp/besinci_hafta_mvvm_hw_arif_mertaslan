package com.example.moviedbapp.favoritelist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviedbapp.R
import com.example.moviedbapp.base.BaseFragment
import com.example.moviedbapp.database.MovieDataRepository
import com.example.moviedbapp.database.MovieDatabase
import com.example.moviedbapp.databinding.FragmentFavoriteListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteListFragment : BaseFragment<FavoriteListViewModel, FragmentFavoriteListBinding>() {

    override lateinit var viewModel: FavoriteListViewModel
    override lateinit var dataBinding: FragmentFavoriteListBinding
    lateinit var movieDatabase: MovieDatabase
    lateinit var movieDataRepository: MovieDataRepository
    lateinit var factory: FavoriteListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentFavoriteListBinding.inflate(inflater)

        movieDatabase = MovieDatabase(requireContext())
        movieDataRepository = MovieDataRepository(movieDatabase)
        factory = FavoriteListViewModelFactory(movieDataRepository)

        viewModel = ViewModelProvider(this, factory)[FavoriteListViewModel::class.java]

        dataBinding.bottomNav.setupWithNavController(this.findNavController())

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllMovies().observe(viewLifecycleOwner, {
                dataBinding.favoriteList.adapter = FavoriteListAdapter(it)
            })
        }

        return dataBinding.root
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }
}
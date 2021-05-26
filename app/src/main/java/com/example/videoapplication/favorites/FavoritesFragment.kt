package com.example.videoapplication.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.videoapplication.databinding.FragmentFavoritesBinding
import com.example.videoapplication.list.ListViewModel

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel : FavoritesViewModel

    val binding by lazy {
        FragmentFavoritesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)

        return binding.root
    }
}
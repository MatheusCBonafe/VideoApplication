package com.example.videoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.videoapplication.databinding.ActivityMainBinding
import com.example.videoapplication.fragments.FavoritesFragment

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpTabs()
    }

    private fun setUpTabs() {
        with(binding) {
            val adapter = ViewPagerAdapter(supportFragmentManager)

            adapter.addFragment(ListFragment(), "Videos")
            adapter.addFragment(FavoritesFragment(), "Favorites")
            viewPager.adapter = adapter
            tabLayout.setupWithViewPager(viewPager)
            tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_format_list_bulleted_24)
            tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_star_24)
        }

    }
}
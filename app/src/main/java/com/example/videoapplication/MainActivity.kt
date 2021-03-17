package com.example.videoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        val recyclerList = generateDummyList(500)

        binding.apply {
            setContentView(root)

            recyclerView.apply {
                adapter = RecyclerAdapter(recyclerList)
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
            }
        }
    }

    fun goToFavoriteScreen(view: View) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.favoriteScreenButton.setOnClickListener {
            val intent = Intent(this, FavoritesScreen::class.java)
            startActivity(intent)
        }
    }

    private fun generateDummyList(size: Int) : List<RecyclerItem> {
        val list = ArrayList<RecyclerItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_anchor
                else -> R.drawable.ic_architecture
            }
            val item = RecyclerItem(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }
}
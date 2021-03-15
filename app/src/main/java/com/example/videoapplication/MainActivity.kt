package com.example.videoapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view_item.*

class MainActivity : AppCompatActivity() {

    private val playbackPosition = 0
    private val rtspUrl = "rstp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerList = generateDummyList(500)

        recycler_view.adapter = RecyclerAdapter(recyclerList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    fun goToFavoriteScreen(view: View) {
        val button = findViewById<Button>(R.id.favorite_screen_button)
        button.setOnClickListener {
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
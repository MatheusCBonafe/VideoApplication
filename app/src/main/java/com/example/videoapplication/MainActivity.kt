package com.example.videoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

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

            tabLayout.apply {
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (getSelectedTabPosition() == 0) {
                            Toast.makeText(this@MainActivity, "Tab " + getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                        } else if (getSelectedTabPosition() == 1) {
                            Toast.makeText(this@MainActivity, "Tab " + getSelectedTabPosition(), Toast.LENGTH_LONG).show();
                        }
                    }
                    override fun onTabReselected(tab: TabLayout.Tab?) {
                        // Handle tab reselect
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        // Handle tab unselect
                    }
                })
            }
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
package com.example.videoapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.videoapplication.databinding.FragmentListBinding

// TODO: Rename parameter arguments, choose names that match

class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val recyclerList = generateDummyList(10)
        setUpAdapter(recyclerList)

        return binding.root
    }

    private fun setUpAdapter(recyclerList: List<RecyclerItem>) {
        with(binding) {
            recyclerView.apply {
                adapter = RecyclerAdapter(recyclerList)
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            }
        }
    }

    private fun generateDummyList(size: Int): List<RecyclerItem> {
        val list = ArrayList<RecyclerItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_anchor
                else -> R.drawable.ic_architecture
            }
           val item = RecyclerItem(drawable, "Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
           list += item
        }
        return list
    }
}
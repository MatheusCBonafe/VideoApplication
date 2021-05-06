package com.example.videoapplication

import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.videoapplication.databinding.FragmentListBinding

// TODO: Rename parameter arguments, choose names that match

class ListFragment : Fragment(), RecyclerAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters

    val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerList = generateDummyList()
        setUpAdapter(recyclerList)

        return binding.root
    }

    private fun setUpAdapter(recyclerList: List<RecyclerItem>) {
        with(binding) {
            recyclerView.apply {
                adapter = RecyclerAdapter(recyclerList, this@ListFragment)
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            }
        }
    }

    override fun onItemClick(position: Int) {
        with(binding) {
            recyclerView.apply {
//                Toast.makeText(activity, "Item $position", Toast.LENGTH_SHORT).show()
//                val clickedItem = recyclerView[position]

                val intent = Intent(requireActivity(), VideoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun generateDummyList(): List<RecyclerItem> {
        return listOf(
            RecyclerItem(
                "https://2.bp.blogspot.com/-bPIkBuAXsbY/XR76sW9L4RI/AAAAAAAAELA/UcveontcdhwsrsHt9V5IqpYe6tMp3QmlACKgBGAs/w0/fantasy-monster-cthulhu-uhdpaper.com-4K-158.jpg",
                "Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                true
            ),

            RecyclerItem(
                "https://2.bp.blogspot.com/-bPIkBuAXsbY/XR76sW9L4RI/AAAAAAAAELA/UcveontcdhwsrsHt9V5IqpYe6tMp3QmlACKgBGAs/w0/fantasy-monster-cthulhu-uhdpaper.com-4K-158.jpg",
                "Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                false
            ),

            RecyclerItem(
                "https://2.bp.blogspot.com/-bPIkBuAXsbY/XR76sW9L4RI/AAAAAAAAELA/UcveontcdhwsrsHt9V5IqpYe6tMp3QmlACKgBGAs/w0/fantasy-monster-cthulhu-uhdpaper.com-4K-158.jpg",
                "Ph'nglui mglw'nafh Cthulhu R'lyeh wgah'nagl fhtagn",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                true
            )
        )
    }
}
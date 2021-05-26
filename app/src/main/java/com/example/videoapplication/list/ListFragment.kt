package com.example.videoapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.videoapplication.databinding.FragmentListBinding
import com.example.videoapplication.list.ListViewModel
import com.example.videoapplication.list.RecyclerAdapter
import com.example.videoapplication.list.RecyclerItem
import com.example.videoapplication.video.VideoActivity

// TODO: Rename parameter arguments, choose names that match

class ListFragment : Fragment(), RecyclerAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters

    private lateinit var listViewModel : ListViewModel

    val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        val recyclerList = generateDummyList()
        setUpAdapter(recyclerList)

        return binding.root
    }

    private fun setUpAdapter(recyclerList: List<RecyclerItem>) {
        with(binding) {
            recyclerView.apply {
                adapter = RecyclerAdapter(
                    recyclerList,
                    this@ListFragment
                )
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            }
        }
    }

    override fun onItemClick(position: Int) {
        with(binding) {
            recyclerView.apply {
                val intent = Intent(requireActivity(), VideoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun generateDummyList(): List<RecyclerItem> {       //todo: use simple api instead
        return listOf(
            RecyclerItem(
                "https://2.bp.blogspot.com/-bPIkBuAXsbY/XR76sW9L4RI/AAAAAAAAELA/UcveontcdhwsrsHt9V5IqpYe6tMp3QmlACKgBGAs/w0/fantasy-monster-cthulhu-uhdpaper.com-4K-158.jpg",
                "Cthulu, The Sleeping God",
                "Cthulu is a Great Old One of great power who lies in a death-like slumber beneath the Pacific Ocean in his sunken city of R'lyeh. He remains a dominant presence in the eldrich dealings on our world. ",
                true
            ),

            RecyclerItem(
                "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/52b5d914-8ec4-4b4d-a1bf-d6885de9f09c/d6ubvy6-50dcb45c-bb4e-40ca-a764-8a5f9ad06758.jpg/v1/fill/w_1024,h_614,q_75,strp/yog_sothoth_rising_by_tentaclesandteeth_d6ubvy6-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NjE0IiwicGF0aCI6IlwvZlwvNTJiNWQ5MTQtOGVjNC00YjRkLWExYmYtZDY4ODVkZTlmMDljXC9kNnVidnk2LTUwZGNiNDVjLWJiNGUtNDBjYS1hNzY0LThhNWY5YWQwNjc1OC5qcGciLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.67hHdPxALt59AWH96g-2jzpx5xkWUTDGymId-ZsLEf8",
                "Yog-Sothoth, The Lurker at The Threshold",
                "Yog-Sothoth is a cosmic entity and Outer God. Born of the Nameless Mist, he is the progenitor of Cthulhu, Hastur the Unspeakable and the ancestor of the Voormi.",
                false
            ),

            RecyclerItem(
                "https://i0.wp.com/actualplay.roleplayingpublicradio.com/wp-content/uploads/blackwind.jpg",
                "Nyarlathotep, The Crawling Chaos",
                "Nyarlathotep, known to many by his epithet The Crawling Chaos, is an Outer God in the Cthulhu Mythos. He is the spawn of Azathoth.",
                true
            )
        )
    }
}
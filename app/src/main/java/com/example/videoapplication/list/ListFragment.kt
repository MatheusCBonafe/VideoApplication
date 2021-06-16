package com.example.videoapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.videoapplication.databinding.FragmentListBinding
import com.example.videoapplication.list.ListViewModel
import com.example.videoapplication.list.RecyclerAdapter
import com.example.videoapplication.list.RecyclerItem
import com.example.videoapplication.video.VideoActivity


// TODO: Rename parameter arguments, choose names that match

class ListFragment : Fragment(), RecyclerAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters

    private val listViewModel: ListViewModel by activityViewModels()


    val movieAdapter by lazy {
        RecyclerAdapter(
            this@ListFragment
        )
    }

    val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun setUpAdapter() {
        with(binding) {
            recyclerView.apply {
                adapter = movieAdapter
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()

        listViewModel.recyclerItems.observe(viewLifecycleOwner, Observer { items ->
            movieAdapter.setItemList(items)
        })

        listViewModel.text()
    }

    override fun onItemClick(position: Int) {
        with(binding) {
            recyclerView.apply {
                val intent = Intent(requireActivity(), VideoActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
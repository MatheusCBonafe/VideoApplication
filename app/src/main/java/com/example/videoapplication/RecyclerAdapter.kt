package com.example.videoapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoapplication.databinding.ItemRecyclerViewBinding

class RecyclerAdapter(private val itemList: List<RecyclerItem>) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = itemList.size
}

class RecyclerViewHolder(private val item: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(item.root) {

    fun bind(recyclerItem: RecyclerItem) {
        with(item) {
            //root.layoutParams = parent.width
            ItemRecyclerViewImageView.setImageResource(recyclerItem.imageResource)
            ItemRecyclerViewTextView1.text = recyclerItem.text1
            ItemRecyclerViewTextView2.text = recyclerItem.text2
        }
    }

}

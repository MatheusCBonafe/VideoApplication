package com.example.videoapplication.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.videoapplication.R
import com.example.videoapplication.databinding.ItemRecyclerViewBinding

class RecyclerAdapter(
    private val listener: ListFragmentCallback
) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private var itemList: MutableList<RecyclerItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context,
                R.anim.animations
            )
        holder.bind(currentItem, listener)
    }

    override fun getItemCount(): Int = itemList.size

    fun setItemList(items: List<RecyclerItem>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(private val item: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(item.root) {

        fun bind(recyclerItem: RecyclerItem, callback : ListFragmentCallback ) {
            with(item) {
                ItemRecyclerViewImageView.load(recyclerItem.imageResource) {
                    transformations(RoundedCornersTransformation(25f))
                    crossfade(true)
                }

                if (recyclerItem.hasFavorite) {
                    ItemRecyclerViewImageView2.setImageResource(R.drawable.ic_baseline_star_24)
                } else {
                    ItemRecyclerViewImageView2.setImageResource(R.drawable.ic_baseline_star_border_24)
                }
                ItemRecyclerViewTextView1.text = recyclerItem.text1
                ItemRecyclerViewTextView2.text = recyclerItem.text2
            }

            itemView.setOnClickListener{
                callback.itemClick(recyclerItem)
            }

        }

      /*  override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION)
        //    listener.onItemClick(position)
        }*/
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

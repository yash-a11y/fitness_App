package com.sevenminuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sevenminuteworkout.databinding.ItemRowBinding



class HistoryAdp(private val items: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdp.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date: String = items.get(position)

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date


        if (position % 2 == 0) {
            holder.llHistoryItemMain.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.llHistoryItemMain.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        // Holds the TextView that will add each item to
        val llHistoryItemMain = binding.historyItemly
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }
}
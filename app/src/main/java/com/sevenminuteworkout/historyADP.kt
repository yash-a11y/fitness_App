package com.sevenminuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sevenminuteworkout.databinding.ItemHistoryBinding


class historyADP(private val items: ArrayList<String>) :
    RecyclerView.Adapter<historyADP.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val date: String = items.get(position)

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        // Updating the background color according to the odd/even positions in list.
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

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        // Holds the TextView that will add each item to
        val llHistoryItemMain = binding.itemhistoryLy
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }
}
// END
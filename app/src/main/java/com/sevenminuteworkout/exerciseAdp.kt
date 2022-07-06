package com.sevenminuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sevenminuteworkout.databinding.ItemsBinding

class exerciseAdp( val item : ArrayList<exercise_model>) : RecyclerView.Adapter<exerciseAdp.exeviewholder>() {

    class exeviewholder(binding :  ItemsBinding) : RecyclerView.ViewHolder(binding.root)
    {
            var tvitem = binding.tvitem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): exeviewholder {
        return exeviewholder(ItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: exeviewholder, position: Int) {
        val objectmodel = item[position]
        holder.tvitem.text = objectmodel.getId().toString()

        when
        {
            objectmodel.getIsselected() ->
            {
                holder.tvitem.background = ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_selected)
                holder.tvitem.setTextColor(Color.parseColor("#2f2f2f"))
            }

            objectmodel.getIsCompleted() ->
            {
                holder.tvitem.background = ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_accent_background)
                holder.tvitem.setTextColor(Color.parseColor("#FFFFFF"))
            }

            else ->
            {
                holder.tvitem.background = ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_grey)
            }
        }
    }

    override fun getItemCount(): Int {

        return item.size
    }
}
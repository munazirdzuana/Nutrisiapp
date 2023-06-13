package com.munaz.nutrisiapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.response.DataItem

class RvAdapter (private val List:List<DataItem>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.rv_artikel, viewGroup, false)
        )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tv_judul)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }

    override fun onBindViewHolder(holder: RvAdapter.ViewHolder, position: Int) {
        holder.tvItem.text = List[position].title
        Glide.with(holder.itemView).load(List[position].image)
            .into(holder.itemView.findViewById(R.id.img_item))
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(List[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int=List.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

}

package com.munaz.nutrisiapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.munaz.nutrisiapp.R

class RvAdapter (private val netv:ArrayList<com.munaz.nutrisiapp.Model>) : RecyclerView.Adapter<RvAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_artikel, parent, false)
        return ListViewHolder(view)
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvRating: TextView =itemView.findViewById(R.id.tv_description)
        val tvTitle: TextView =itemView.findViewById(R.id.tv_judul)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: com.munaz.nutrisiapp.Model)
    }

    override fun onBindViewHolder(holder: RvAdapter.ListViewHolder, position: Int) {
        val (title, rating) = netv[position]
        holder.tvRating.text = rating
        holder.tvTitle.text = title
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(netv[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int=netv.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

}

package com.munaz.nutrisiapp.ui.favMakanan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.munaz.nutrisiapp.R

class FavRvAdapter (private val netv:ArrayList<com.munaz.nutrisiapp.Model>) :
    RecyclerView.Adapter<FavRvAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: FavRvAdapter.OnItemClickCallback

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView =itemView.findViewById(R.id.makanan)
        val tvDesc:TextView =itemView.findViewById(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view:View =LayoutInflater.from(parent.context).inflate(R.layout.item_makanan, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int= netv.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title,desc)=netv[position]
        holder.tvTitle.text=title
        holder.tvDesc.text=desc
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(netv[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: com.munaz.nutrisiapp.Model)
    }

    fun setOnItemClickCallback(onItemClickCallback: FavRvAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}
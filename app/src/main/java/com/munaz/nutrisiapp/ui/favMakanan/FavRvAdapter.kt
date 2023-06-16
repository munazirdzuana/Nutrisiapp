package com.munaz.nutrisiapp.ui.favMakanan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.munaz.nutrisiapp.R
import com.munaz.nutrisiapp.data.response.FoodsItem
import com.munaz.nutrisiapp.ui.home.RvAdapterResep

class FavRvAdapter (private val List:List<FoodsItem>) :
    RecyclerView.Adapter<FavRvAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: FavRvAdapter.OnItemClickCallback

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvmakan: TextView = view.findViewById(R.id.makanan)
        val tvHarga: TextView = view.findViewById(R.id.tv_rp)
        val tvdesc: TextView = view.findViewById(R.id.tv_desc)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        FavRvAdapter.ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_makanan, viewGroup, false)
        )

    override fun getItemCount(): Int= List.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvdesc.text=List[position].description
        holder.tvmakan.text = List[position].foodRecipe[0].name
        holder.tvHarga.text = List[position].price.toString()
        Glide.with(holder.itemView).load(List[position].foodRecipe[0].image)
            .into(holder.itemView.findViewById(R.id.imgmakan))
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(List[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FoodsItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}
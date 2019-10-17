package com.example.projetoandroidmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class ItemAdapter(param: Any?, item: Any?) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    lateinit var context: Context
    lateinit var item: ArrayList<Item>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = item[position].title
        holder.description.text = item[position].description
        Picasso
            .get()
            .load(item[position].image)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return item.size
        }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var title: TextView = itemView.item_title
         var description: TextView = itemView.item_description
         var image: ImageView = itemView.item_image
    }


}

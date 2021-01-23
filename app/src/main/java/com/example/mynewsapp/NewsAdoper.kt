package com.example.mynewsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdoper(private val listner:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val item = ArrayList<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news, parent
            , false
        )
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listner.onItemClicked(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = item[position]
        holder.titleView.text = currentItem.title
    }

    fun update(updateNew: ArrayList<News>) {
      item.clear()
        item.addAll(updateNew)
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView:TextView=itemView.findViewById(R.id.title)
}
interface NewsItemClicked{
    fun onItemClicked(item:News)
}
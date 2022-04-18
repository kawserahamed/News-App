package org.primeit.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.primeit.newsapp.model.NewsModel
import org.primeit.newsapp.R

class MyAdapter(
    private val context: Context,
    private val listener: NewsItemClicked
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val newsList: ArrayList<NewsModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = newsList[position]

        holder.title.text = news.title
        holder.author.text = news.author
        Glide.with(context).load(news.imageUrl).into(holder.image)

        holder.itemView.setOnClickListener {
            listener.onItemClicked(newsList[position])
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNews(updateNews: ArrayList<NewsModel>) {
        newsList.clear()
        newsList.addAll(updateNews)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById<TextView>(R.id.tv_title)
        val author: TextView = itemView.findViewById<TextView>(R.id.tv_author)
        val image: ImageView = itemView.findViewById<ImageView>(R.id.iv_newsId)


    }


}


interface NewsItemClicked {

    fun onItemClicked(item: NewsModel)


}
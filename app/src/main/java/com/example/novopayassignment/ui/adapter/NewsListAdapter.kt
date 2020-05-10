package com.example.novopayassignment.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.novopayassignment.R
import com.example.novopayassignment.model.Articles
import com.example.novopayassignment.ui.DetailActivity

class NewsListAdapter(var context: Context, var newsList: ArrayList<Articles>) :
    RecyclerView.Adapter<NewsListAdapter.ArticleViewHolder>() {

    lateinit var onItemClick: ((String) -> Unit?)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)

        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val news = newsList[position]

        holder.tvSource.text = news.source.name
        holder.tvHead.text = news.title
        holder.tvDate.text = news.publishedAt
        Glide.with(context)
            .load(news.urlToImage)
            .into(holder.ivNews)
        holder.clNews.setOnClickListener {
            val detailIntent: Intent = Intent(context, DetailActivity::class.java)
            detailIntent.putExtra("url", news.url)
            detailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(detailIntent)
        }

    }

    override fun getItemCount(): Int = newsList.size

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var clNews: ConstraintLayout = itemView.findViewById(R.id.clNews)
        internal var ivNews: ImageView = itemView.findViewById(R.id.ivNews)
        internal var tvSource: TextView = itemView.findViewById(R.id.tvSource)
        internal var tvHead: TextView = itemView.findViewById(R.id.tvHead)
        internal var tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }
}
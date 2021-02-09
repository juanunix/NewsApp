package com.news.app.Utils.Adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.news.app.Model.Article
import com.news.app.R
import kotlinx.android.synthetic.main.headline_gridlayout_item.view.*


class HeadlinesGridAdapter(private val users: ArrayList<Article>) :
    RecyclerView.Adapter<HeadlinesGridAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: Article) {
            //Bind data to Views
            itemView.apply {
                newsAuthor.text = news.author
                newsTitle.text = news.title
                //Set image from Image URL using GLIDE Library
                Glide.with(newsImage.context)
                    .load(news.urlToImage)
                    .placeholder(R.drawable.loading)
                    .into(newsImage)

            }
            //Get url of the article and launch an intent to open web browser on item click
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.headline_gridlayout_item, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addNews(users: List<Article>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}
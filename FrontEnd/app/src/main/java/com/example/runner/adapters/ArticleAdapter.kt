package com.example.runner.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.runner.R
import com.example.runner.model.Article
import com.example.runner.utils.HelperFunctions
import com.example.runner.utils.HelperFunctions.launchURL
import com.example.runner.viewholder.ArticleViewHolder

class ArticlesAdapter constructor(val data: MutableList<Article>) :
    ClassicAdapter<ArticleViewHolder, Article>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_feed_item, parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val article = data[position]

        holder.apply {
            articleTitle!!.text = article.title
            articleDescription!!.text = article.content

            HelperFunctions.usePicasso(article.image!!, R.drawable.active_calories, articleImage!!)

            itemView.setOnClickListener {

                launchURL(
                    itemView.context,
                    data[position].link!!
                )

            }
        }
    }


}

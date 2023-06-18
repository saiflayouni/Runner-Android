package com.example.runner.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.runner.R

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var articleTitle: TextView? = null
    var articleDescription: TextView? = null
    var articleImage: ImageView? = null

    init {
        articleTitle = itemView.findViewById(R.id.artTitle)
        articleDescription = itemView.findViewById(R.id.artDesc)
        articleImage = itemView.findViewById(R.id.artImage)
    }
}
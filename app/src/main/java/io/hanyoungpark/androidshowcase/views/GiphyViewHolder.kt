package io.hanyoungpark.androidshowcase.views

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.hanyoungpark.androidshowcase.R

class GiphyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.image)
    val textView: TextView = itemView.findViewById(R.id.textView)
}
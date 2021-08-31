package io.hanyoungpark.androidshowcase.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.hanyoungpark.androidshowcase.R
import io.hanyoungpark.androidshowcase.models.DataModel

class GiphyAdapter(private val context: Context): RecyclerView.Adapter<GiphyViewHolder>() {
    private val data = mutableListOf<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_image, null)
        return GiphyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val it = data[position]
        it.images?.original?.url?.let {
            Glide.with(context).load(it).into(holder.imageView)
        }
        it.title?.let {
            holder.textView.text = it
        }
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    fun reset() {
        data.clear()
        notifyDataSetChanged()
    }

    fun add(newData: List<DataModel>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }
}
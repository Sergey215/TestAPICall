package com.vshcherban.testapicall

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vshcherban.testapicall.models.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter(private val listener: (Post) -> Unit) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val data: ArrayList<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(data[position], listener)
    }

    fun updateData(posts: List<Post>) {
        data.clear()
        data.addAll(posts)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(data: Post, listener: (Post) -> Unit) {
            itemView.title.text = data.title
            itemView.description.text = data.body
            itemView.setOnClickListener { listener(data) }
        }
    }
}


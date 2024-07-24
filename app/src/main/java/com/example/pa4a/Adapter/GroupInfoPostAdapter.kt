package com.example.pa4a.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pa4a.R
import com.example.pa4a.dataModel.GroupPostResponse

class GroupInfoPostAdapter(private val posts: List<GroupPostResponse>) : RecyclerView.Adapter<GroupInfoPostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author: TextView = view.findViewById(R.id.post_author)
        val content: TextView = view.findViewById(R.id.post_content)
        val image: ImageView = view.findViewById(R.id.post_image)
        val createdAt: TextView = view.findViewById(R.id.post_created_at)
        val likes: TextView = view.findViewById(R.id.post_likes)
        val comments: TextView = view.findViewById(R.id.post_comments)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_post_card, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.author.text = post.author
        holder.content.text = post.content
        if (post.image != null) {
            holder.image.visibility = View.VISIBLE
            Glide.with(holder.itemView.context).load(post.image).into(holder.image)
        } else {
            holder.image.visibility = View.GONE
        }
        holder.createdAt.text = post.createdAt
        holder.likes.text = "Likes: ${post.likes.size}"
        holder.comments.text = "Comments: ${post.comments.size}"
    }

    override fun getItemCount(): Int = posts.size
}
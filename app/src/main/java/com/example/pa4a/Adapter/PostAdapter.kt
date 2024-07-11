package com.example.pa4a.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pa4a.R
import com.example.pa4a.dataModel.UserPostResponse

class PostAdapter(private val posts: List<UserPostResponse>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //val content: TextView = view.findViewById(R.id.content)
        //val author: TextView = view.findViewById(R.id.author)
        val code : TextView = view.findViewById(R.id.code)

        //val likes : TextView = view.findViewById(R.id.likes)
        //val language : TextView = view.findViewById(R.id.language)
        // ajoutez d'autres vues ici si nécessaire
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        //holder.content.text = post.content
        //holder.author.text = post.author
        holder.code.text = "```" + post.language + "\n" + post.code + "\n```"
        //holder.likes.text = post.likes.size.toString()
        //holder.language.text = post.language
        // mettez à jour d'autres vues ici si nécessaire
    }

    override fun getItemCount() = posts.size
}
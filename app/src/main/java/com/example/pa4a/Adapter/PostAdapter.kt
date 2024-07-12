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
        val author: TextView = view.findViewById(R.id.author)
        val code  = view.findViewById<jp.wasabeef.richeditor.RichEditor>(R.id.code)
        val createdAt : TextView = view.findViewById(R.id.created_at)
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
        holder.author.text = post.author
        println("language: ${post.language}")
        val limitedCode = if (post.code.length > 1000) post.code.substring(0, 1000) else post.code
        val standardizedCode = limitedCode.replace("\r\n", "\n")


        holder.code.setInputEnabled(false)
        holder.code.html ="""
                    <pre style="color: #000000;">
                        <code>  
                      ${standardizedCode.trimIndent().trimStart().replaceFirst("\n", "")}
                        </code>
                    </pre>
                """
        holder.createdAt.text = post.createdAt

        //holder.likes.text = post.likes.size.toString()
        //holder.language.text = post.language
        // mettez à jour d'autres vues ici si nécessaire
    }

    override fun getItemCount() = posts.size
}
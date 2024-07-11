package com.example.pa4a.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pa4a.R
import com.example.pa4a.dataModel.UsersResponse
import com.bumptech.glide.Glide
import com.example.pa4a.dataModel.UserResponse

class MyAdapter(private val users: List<UserResponse>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val username: TextView = itemView.findViewById(R.id.username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.username.text = user.username
        Glide.with(holder.itemView.context).load(user.profilePic).into(holder.image)
    }

    override fun getItemCount() = users.size
}
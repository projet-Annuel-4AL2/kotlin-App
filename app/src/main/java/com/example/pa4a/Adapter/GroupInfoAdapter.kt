package com.example.pa4a.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pa4a.R
import com.example.pa4a.dataModel.GroupResponse

class GroupInfoAdapter(private val groupInfo: GroupResponse) : RecyclerView.Adapter<GroupInfoAdapter.GroupInfoViewHolder>() {

    class GroupInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groupPic: ImageView = view.findViewById(R.id.group_pic)
        val name: TextView = view.findViewById(R.id.name)
        val description: TextView = view.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_card, parent, false)
        return GroupInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupInfoViewHolder, position: Int) {
        holder.name.text = groupInfo.name
        holder.description.text = groupInfo.description
        val imageUrl = "http://10.0.2.2" + groupInfo.groupPic

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .error(R.drawable.groups)
            .into(holder.groupPic)
    }

    override fun getItemCount(): Int = 1 // Only one group info
}
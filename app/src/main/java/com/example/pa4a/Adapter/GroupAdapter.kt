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

class GroupAdapter(private val groups: List<GroupResponse>) : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {

    class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val groupPic: ImageView = view.findViewById(R.id.group_pic)
        val name: TextView = view.findViewById(R.id.name)
        val description: TextView = view.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_card, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = groups[position]
        holder.name.text = group.name
        holder.description.text = group.description
        val imageUrl = "http://10.0.2.2" + group.groupPic

        if (group.groupPic != null ) {
            Glide.with(holder.itemView.context).load(imageUrl) .error(R.drawable.groups).into(holder.groupPic)
        } else {
            holder.groupPic.setImageResource(R.drawable.groups)
        }
    }

    override fun getItemCount(): Int = groups.size
}
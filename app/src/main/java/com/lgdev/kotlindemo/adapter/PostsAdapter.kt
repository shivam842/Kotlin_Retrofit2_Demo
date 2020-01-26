package com.lgdev.kotlindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lgdev.kotlindemo.R
import com.lgdev.kotlindemo.model.Post
import kotlinx.android.synthetic.main.row_rv_user.view.*

class PostsAdapter(internal var context: Context, internal var posList: List<Post>) :
    RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_rv_user, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_title.text = posList[position].title
        holder.txt_content.text =
            StringBuilder(posList[position].body.substring(0, 100)).append("...").toString()
        holder.txt_auther.text = posList[position].userId.toString()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_auther = itemView.txt_auther
        val txt_content = itemView.txt_content
        val txt_title = itemView.txt_title
    }
}
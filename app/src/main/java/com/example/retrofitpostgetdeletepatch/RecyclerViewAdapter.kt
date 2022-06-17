package com.example.retrofitpostgetdeletepatch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RvViewHolder>() {

    var userList = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.RvViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return RvViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.RvViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class RvViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvName = view.textViewName
        val tvEmail = view.textViewEmail
        val tvStatus = view.textViewStats

        fun bind(data: User) {
            tvName.text =  data.name
            tvEmail.text = data.email
            tvStatus.text = data.status
        }
    }
}

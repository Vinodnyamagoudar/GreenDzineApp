package com.example.greendzineapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductivityAdapter (private val items: List<ProductivityData>):
    RecyclerView.Adapter<ProductivityAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val prodTextView: TextView = itemView.findViewById(R.id.tv_productivity)
            val percentTextView: TextView = itemView.findViewById(R.id.tv_percentage)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.prodTextView.text = item.prod
            holder.percentTextView.text = "Productivity: ${item.percent}%"
        }

        override fun getItemCount(): Int {
            return items.size
        }
}
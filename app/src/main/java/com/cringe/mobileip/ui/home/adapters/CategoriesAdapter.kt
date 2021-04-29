package com.cringe.mobileip.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cringe.mobileip.R

class CategoriesAdapter(
    private val categories: List<CategoryAndStatus>
): RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.categoryNameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.orders_category_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = categories[position].category.name

        holder.textView.background = ContextCompat.getDrawable(
            holder.textView.context,
            if (categories[position].status)
                R.drawable.order_category_selected
            else
                R.drawable.order_category
        )

        holder.textView.setOnClickListener {
            categories[position].status = !categories[position].status
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = categories.size
}
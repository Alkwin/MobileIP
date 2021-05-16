package com.cringe.mobileip.ui.home.needier.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cringe.mobileip.R

class CategoriesAdapter(
    private val categories: MutableList<CategoryAndStatus>
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
        val context = holder.textView.context

        holder.textView.text = categories[position].category.name

        if (categories[position].status) {
            holder.textView.background = ContextCompat.getDrawable(
                context, R.drawable.order_category_selected
            )
            holder.textView.setTextColor(context.resources.getColor(R.color.design_default_color_on_secondary, context.theme))
        } else {
            holder.textView.background = ContextCompat.getDrawable(
                holder.textView.context, R.drawable.order_category
            )
            holder.textView.setTextColor(context.resources.getColor(R.color.design_default_color_on_primary, context.theme))
        }
        holder.textView.setOnClickListener {
            categories[position].status = !categories[position].status
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = categories.size
}
package com.cringe.mobileip.ui.home.needier.adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cringe.mobileip.R
import com.cringe.mobileip.server.model.utils.Tag

class TagsAdapter(
    private val tags: MutableList<TagAndWeight>
): RecyclerView.Adapter<TagsAdapter.ViewHolder>() {
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

        holder.textView.text = tags[position].tag.name

        if (tags[position].weight > 0) {
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
            getAddWeightDialog(position, context)
                .apply {
                    setOnDismissListener {
                        notifyItemChanged(position)
                    }
                    show()
                }
        }
    }

    private fun getAddWeightDialog(index: Int, context: Context): Dialog {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.set_weight_dialog, null)
        view.findViewById<TextView>(R.id.tagName).text = tags[index].tag.name
        if (tags[index].weight != 0.0)
                view.findViewById<TextView>(R.id.quantityEditText).text = tags[index].weight.toString()

        return AlertDialog
            .Builder(context)
            .setView(view)
            .setNegativeButton("Cancel") { dialog, int ->
                dialog.dismiss()
            }
            .setNeutralButton("Delete") { dialog, int ->
                tags[index].weight = 0.0
                dialog.dismiss()
            }
            .setPositiveButton("Add") { dialog, int ->
                val numberString: String = view.findViewById<EditText>(R.id.quantityEditText).text.toString()
                var value = 0.0
                try {
                    value = numberString.toDouble()
                } catch (e: Exception) { }

                tags[index].weight = value
                dialog.dismiss()
            }
            .create()
    }

    override fun getItemCount() = tags.size
}
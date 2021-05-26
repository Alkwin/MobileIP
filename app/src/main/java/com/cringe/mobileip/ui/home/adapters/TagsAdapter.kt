package com.cringe.mobileip.ui.home.adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cringe.mobileip.R
import com.cringe.mobileip.server.model.utils.tags.Product
import com.cringe.mobileip.server.model.utils.tags.Service
import com.cringe.mobileip.server.model.utils.tags.TagType

class TagsAdapter(
    val tags: MutableList<TagStatus>
): RecyclerView.Adapter<TagsAdapter.ViewHolder>() {
    @Volatile
    private var currentType: TagType? = null

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

        when {
            tags[position].property.isSelected() -> {
                holder.textView.background = ContextCompat.getDrawable(
                    context, R.drawable.order_category_selected
                )
                holder.textView.setTextColor(context.resources.getColor(R.color.design_default_color_on_secondary, context.theme))
            }
            currentType != null && currentType != tags[position].property.type -> {
                holder.textView.background = ContextCompat.getDrawable(
                    holder.textView.context, R.drawable.order_category_disabled
                )
                holder.textView.setTextColor(context.resources.getColor(R.color.design_default_color_on_primary, context.theme))
            }
            else -> {
                if (tags[position].property.type == TagType.PRODUCT) {
                    holder.textView.background = ContextCompat.getDrawable(
                        holder.textView.context, R.drawable.order_category_product
                    )
                } else {
                    holder.textView.background = ContextCompat.getDrawable(
                        holder.textView.context, R.drawable.order_category_default
                    )
                }
                holder.textView.setTextColor(context.resources.getColor(R.color.design_default_color_on_primary, context.theme))
            }
        }

        holder.textView.setOnClickListener {
            val property = tags[position].property

            when {
                currentType == null -> {
                    currentType = property.type
                    notifyDataSetChanged()
                }
                currentType != property.type -> return@setOnClickListener
            }

            when (property) {
                is Product -> {
                    getAddWeightDialog(position, context)
                        .apply {
                            setOnDismissListener {
                                notifyItemChanged(position)
                                refreshCurrentType()
                            }
                            show()
                        }
                }
                is Service -> {
                    property.weight = !property.weight
                    notifyItemChanged(position)
                    refreshCurrentType()
                }
            }
        }
    }

    private fun refreshCurrentType() {
        for (it in tags) {
            if (it.property.isSelected()) {
                currentType = it.property.type
                return
            }
        }
        currentType = null
        notifyDataSetChanged()
    }

    private fun getAddWeightDialog(index: Int, context: Context): Dialog {
        val property = tags[index].property as Product
        val view = LayoutInflater.from(context)
            .inflate(R.layout.set_weight_dialog, null)
        view.findViewById<TextView>(R.id.tagName).text = tags[index].tag.name
        if (property.weight != 0.0)
                view.findViewById<TextView>(R.id.quantityEditText).text = property.weight.toString()

        return AlertDialog
            .Builder(context)
            .setView(view)
            .setNegativeButton("Cancel") { dialog, int ->
                dialog.dismiss()
            }
            .setNeutralButton("Delete") { dialog, int ->
                property.weight = 0.0
                dialog.dismiss()
            }
            .setPositiveButton("Add") { dialog, int ->
                val numberString: String = view.findViewById<EditText>(R.id.quantityEditText).text.toString()
                var value = 0.0
                try {
                    value = numberString.toDouble()
                } catch (e: Exception) { }

                property.weight = value
                dialog.dismiss()
            }
            .create()
    }

    override fun getItemCount() = tags.size
}
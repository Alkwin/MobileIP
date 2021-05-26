package com.cringe.mobileip.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cringe.mobileip.R
import com.cringe.mobileip.server.model.utils.HelperData

class HelperAdapter(
    private val helpers: List<HelperData>,
    private val onSelected: (HelperData) -> Unit
): RecyclerView.Adapter<HelperAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var username: TextView = itemView.findViewById(R.id.username)
        var address: TextView = itemView.findViewById(R.id.address)
        var distance: TextView = itemView.findViewById(R.id.distance)
        var score: TextView = itemView.findViewById(R.id.score)
        var commonResources: TextView = itemView.findViewById(R.id.commonResources)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chose_helper_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.username.context

        with(helpers[position]) {
            holder.username.text = username
            holder.address.text = adress
            holder.distance.text = distance.toString()
            holder.score.text = score.toString()
            holder.commonResources.text = commonResources
                .map { "${it.key}${if(it.value >= 0) ": ${it.value}" else ""}"  }
                .joinToString(separator = "\n")
        }

        holder.itemView.setOnClickListener { onSelected(helpers[position]) }
    }
    override fun getItemCount() = helpers.size
}
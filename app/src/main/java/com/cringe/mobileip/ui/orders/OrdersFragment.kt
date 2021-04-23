package com.cringe.mobileip.ui.orders

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cringe.mobileip.R
import com.cringe.mobileip.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    private lateinit var ordersViewModel: OrdersViewModel
    private var _binding: FragmentOrdersBinding? = null
    private lateinit var ordersList: LinearLayout

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ordersViewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)

        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val root: View = binding.root
        ordersList = binding.ordersList
        val addButton = binding.button

        addButton.setOnClickListener {
            // if the context is not null
            //context?.let { it1 -> ordersViewModel.addTextToOrderList("Text", ordersList, it1) }
            val rowView = inflater.inflate(R.layout.field, null)
            ordersList.addView(rowView)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.cringe.mobileip.ui.orders

import android.app.ActionBar
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrdersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    fun addTextToOrderList(message: String,
                           ordersList: LinearLayout,
                           context: Context
    ) {
        val textBoxParameters = ActionBar.LayoutParams(
            ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT
        )
        val tv = TextView(context)
        tv.layoutParams = textBoxParameters
        tv.text = message
        ordersList.addView(tv)
    }

    val text: LiveData<String> = _text
}
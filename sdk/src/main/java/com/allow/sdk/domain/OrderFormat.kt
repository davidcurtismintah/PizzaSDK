package com.allow.sdk.domain

import android.content.Context
import com.allow.sdk.R
import kotlinx.android.synthetic.main.view_order_fragment.*
import java.text.NumberFormat
import java.util.*

class OrderFormat private constructor(val context: Context, val order: Order) {

    companion object {
        fun getInstance(context: Context, order: Order) = OrderFormat(context, order)
    }

    fun formatPizza(): CharSequence = context.getString(
        R.string.pizza_description,
        if (order.items.size == 1 || order.items[0] == order.items[1]){
            order.items[0].name
        } else {
            order.items.joinToString(separator = ", ", transform = { flavor -> flavor.name })
        }
    )

    fun formatCost(): CharSequence {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        val currency = format.format(order.cost)
        return currency
    }
}
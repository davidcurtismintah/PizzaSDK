package com.allow.sdk.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.allow.sdk.domain.CostCalculator
import com.allow.sdk.domain.Flavor
import com.allow.sdk.domain.Order

class MainViewModel : ViewModel() {

    companion object {
        val FLAVORS = arrayOf(
            "Cheese",
            "Pepperoni",
            "Vegetarian"
        )
        val PRICES = mapOf(
            0 to 8.0,
            1 to 10.0,
            2 to 9.0
        )
    }

    val orderLiveData = MutableLiveData<Order>()
    private val mCostCalculator = CostCalculator()

    fun order(firstFlavor: Int, secondFlavor: Int) {
        val nameOfFirstFlavor = FLAVORS[firstFlavor]
        val nameOfSecondFlavor = FLAVORS[secondFlavor]

        val priceOfFirstFlavor = PRICES[firstFlavor] ?: 0.0
        val priceOfSecondFlavor = PRICES[secondFlavor] ?: 0.0

        val orderCost = mCostCalculator.getCost(priceOfFirstFlavor, priceOfSecondFlavor)

        orderLiveData.value = Order(
            listOf(
                Flavor(firstFlavor, nameOfFirstFlavor),
                Flavor(secondFlavor, nameOfSecondFlavor)
            ), orderCost
        )
    }

    fun order(flavor: Int) {
        val nameOfFlavor = FLAVORS[flavor]
        val priceOfFlavor = PRICES[flavor] ?: 0.0

        val orderCost = mCostCalculator.getCost(priceOfFlavor)

        orderLiveData.value = Order(
            listOf(
                Flavor(flavor, nameOfFlavor)
            ), orderCost
        )
    }

}

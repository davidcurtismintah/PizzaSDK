package com.allow.sdk.domain

class CostCalculator {
    fun getCost(priceOfFirstFlavor: Double, priceOfSecondFlavor: Double) =
        priceOfFirstFlavor / 2.0 + priceOfSecondFlavor / 2.0

    fun getCost(priceOfFlavor: Double) = priceOfFlavor
}
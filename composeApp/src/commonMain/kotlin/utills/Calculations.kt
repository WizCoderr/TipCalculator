package utills

fun calculateTip(totalBill: Double, tipPercentage: Int): Double {
    return if (totalBill > 1 && totalBill.toString().isNotEmpty())
        (totalBill * tipPercentage) / 100 else 0.0
}

fun calculateTotalTip(totalBill: Double, tipPercentage: Int, splitBy: Int): Double {
    val bill = calculateTip(totalBill = totalBill, tipPercentage = tipPercentage) + totalBill
    return (bill / splitBy)

}
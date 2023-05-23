import kotlin.math.pow

fun main() {
    val newParameter = readln()
    val newParameterValue = readln().toInt()
    changeParameter(newParameter, newParameterValue)

}

fun compoundInterest(startAmount: Int = 1000, yearlyPercent: Double = 5.0, numOfYears: Int = 10) {
    val helper = (1 + yearlyPercent / 100).pow(numOfYears)
    return println((startAmount * helper).toInt())
}


fun changeParameter(newParameter: String, newParameterValue: Int) {
    when(newParameter) {
        "amount" -> compoundInterest(newParameterValue, yearlyPercent = 5.0, numOfYears =  10)
        "percent" -> compoundInterest(startAmount = 1000, newParameterValue.toDouble(), numOfYears =  10)
        "years" -> compoundInterest(startAmount = 1000, yearlyPercent = 5.0, newParameterValue)
    }
}
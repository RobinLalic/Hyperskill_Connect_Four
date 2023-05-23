fun main() {
    val firstNumber = readln().toInt()
    val secondNumber = readln().toInt()
    val thirdNumber = readln().toInt()

    val isTrue:Boolean = (firstNumber>0 && secondNumber<=0 && thirdNumber<=0)
            ||(firstNumber<=0 && secondNumber>0 && thirdNumber<=0)
            ||(firstNumber<=0 && secondNumber<=0 && thirdNumber>0)

    println(isTrue)

}
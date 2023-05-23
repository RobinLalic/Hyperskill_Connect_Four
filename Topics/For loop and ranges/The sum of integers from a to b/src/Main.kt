fun main() {
    val firstInput = readln().toInt()
    val secondInput = readln().toInt()
    var sum = 0

    for (number in firstInput..secondInput)
        sum+=number
    println(sum)
}
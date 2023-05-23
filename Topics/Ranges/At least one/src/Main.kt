fun main() {
    val firstRangeFirst = readln().toInt()
    val firstRangeSecond = readln().toInt()
    val secondRangeFirst = readln().toInt()
    val secondRangeSecond = readln().toInt()
    val input = readln().toInt()

    println(input in firstRangeFirst..firstRangeSecond
            || input in secondRangeFirst..secondRangeSecond)
}
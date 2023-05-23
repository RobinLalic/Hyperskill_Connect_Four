fun main() {
    val n = readln().toInt()

    var longestSeqLength = 1
    var currentSeqLength = 1
    var previousNumber = readln().toInt()

    for (i in 2..n) {
        val number = readln().toInt()

        if (number >= previousNumber) {
            currentSeqLength++
        } else {
            if (currentSeqLength > longestSeqLength) {
                longestSeqLength = currentSeqLength
            }
            currentSeqLength = 1
        }

        previousNumber = number
    }

    if (currentSeqLength > longestSeqLength) {
        longestSeqLength = currentSeqLength
    }

    println(longestSeqLength)
}
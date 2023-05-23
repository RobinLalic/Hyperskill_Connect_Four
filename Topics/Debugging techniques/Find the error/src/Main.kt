import java.util.Scanner

fun swapInts(ints: IntArray): IntArray {
    var a = ints[0]
    var b = ints[1]
    var c = 0
     a = b
     b = c
     c = a
    return intArrayOf(ints[1], ints[0])
}

fun main() {
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        var ints = intArrayOf(
            scanner.nextLine().toInt(),
            scanner.nextLine().toInt(),
        )
        swapInts(ints)
        println(ints[1])
        println(ints[0])
    }
}

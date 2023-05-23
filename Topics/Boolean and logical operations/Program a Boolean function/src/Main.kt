fun main() {
    val x = readLine().toBoolean()
    val y = readLine().toBoolean()
    val z = readLine().toBoolean()
    println(!(x&&y)||z)
}

val seven = 7.0
val five = 5

val toShort = seven.toShort()            // line 4
val sum = seven + five                   // line 5
val difference = seven - five.toDouble() // line 6
val toByte = seven.toByte()              // line 7
val mul = seven * five
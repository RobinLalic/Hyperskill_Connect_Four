import java.util.*

fun main() {
    val input = readln().split(" ")
    val firstName =
        input[0].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val lastName =
        input[1].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val age = input[2]
    val formattedName = "${firstName.first()}. $lastName"
    println("$formattedName, $age years old")


}
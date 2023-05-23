fun main() {
    val firstString = readln()
    val operation = readln()
    val secondString = readln()
    when(operation){
        "equals" -> println(firstString == secondString)
        "plus" -> println(firstString + secondString)
        "endsWith" -> println(firstString.endsWith(secondString))
        else -> println("Unknown operation")

    }
}
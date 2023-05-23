fun main() {
    val input = readln().toInt()
    var i = 1
    var number = 0
    while (number < input){
        number = i*i
        if (number <= input)
            println("$number")
        i++

    }
}
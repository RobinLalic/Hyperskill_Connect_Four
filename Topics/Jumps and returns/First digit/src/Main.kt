fun main() {
    val input = readln()
    for (i in input.toCharArray()){
        if (i in '1'..'9') {
            println(i)
            break
        }
    }
}
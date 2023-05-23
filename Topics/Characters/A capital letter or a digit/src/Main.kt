fun main() {
    val input = readln().first()
    if(input.isUpperCase() || input.isDigit() && input!= '0')
        print("true")
    else print("false")
}
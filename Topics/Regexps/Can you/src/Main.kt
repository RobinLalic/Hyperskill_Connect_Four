fun main() {
    val answer = readln()
    val pattern = "^(I can do my homework on time!|I can't do my homework on time!)$".toRegex()
    val isOnTime = pattern.matches(answer)
    println(isOnTime)

}
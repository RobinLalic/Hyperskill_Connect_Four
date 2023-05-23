fun main() {
    val totalSeconds = System.currentTimeMillis() / 1000 // do not change this line
    val hours = totalSeconds / 3600 % 24
    val minutes = totalSeconds / 60 % 60
    val remainingSeconds = totalSeconds % 60
    println("$hours:$minutes:$remainingSeconds")
}
fun main() {
    val(hours, minutes, seconds) = readln().split(' ')
    val(day, month, year) = readln().split(' ')

    val time = "$hours:$minutes:$seconds"
    val date = "$day/$month/$year"

    print("$time $date")
}
fun main() {
    val listSize = readln().toInt()
    val listOfNumbers = List(listSize) { readln().toInt() }
    val (p, m) = readln().split(" ").map { it.toInt() }

    var isNear = false

    for(i in 0 until listSize - 1){
        if((listOfNumbers[i] == p && listOfNumbers[i + 1] == m) || (listOfNumbers[i] == m && listOfNumbers[i+1] == p)){
            isNear = true
            break
        }
    }
    if (isNear)
        println("NO")
    else
        println("YES")
}
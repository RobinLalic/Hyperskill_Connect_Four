fun main() {
    val input = readln()
    var count = 0
    var i = 0
    while (i < input.length - 2) {
        val letterOne = input[i]
        val letterTwo = input[i + 1]
        val letterThree = input[i + 2]
        if ((isVowel(letterOne) && isVowel(letterTwo) && isVowel(letterThree)) ||
            (!(isVowel(letterOne)) && !(isVowel(letterTwo)) && !(isVowel(letterThree)))
        ) {
            count++
            i += 2
        } else
            i++
    }
    println(count)
}

fun isVowel(c: Char): Boolean {
    return c in listOf('a', 'e', 'i', 'o', 'u', 'y')
}
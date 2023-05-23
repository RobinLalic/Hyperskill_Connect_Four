fun solution(strings: List<String>, str: String): Int {
    var occurrences = 0
    for (item in strings)
        if (item == str)
            occurrences++
    return occurrences
}
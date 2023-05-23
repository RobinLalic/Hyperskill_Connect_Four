fun countUniqueChars(sequence: CharSequence): Int {
    val uniqueCharacters = HashSet<Char>()
    for (char in sequence) {
        uniqueCharacters.add(char)
    }
    return uniqueCharacters.size
}
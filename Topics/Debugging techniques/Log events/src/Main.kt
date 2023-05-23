fun String?.capitalize(): String? {
    return when {
        isNullOrBlank() -> this
        length == 1 -> uppercase()
        else -> this[0].uppercase() + substring(1)
    }
}
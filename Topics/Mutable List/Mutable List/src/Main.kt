import java.util.Collections

fun names(namesList: List<String>): List<String> {
    var count = 0
    val nameCount = mutableListOf<String>()

    for (name in namesList.distinct()){
        nameCount.add("The name $name is repeated " + Collections.frequency(namesList, name) + " times")
    }

    return nameCount
}
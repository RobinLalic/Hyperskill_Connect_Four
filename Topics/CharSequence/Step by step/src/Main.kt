import java.lang.StringBuilder

fun removeEveryOtherChar(input: CharSequence): CharSequence {
    val builder = StringBuilder()
    for (i in input.indices step 2){
      builder.append(input[i])
    }
    return builder.toString()
}
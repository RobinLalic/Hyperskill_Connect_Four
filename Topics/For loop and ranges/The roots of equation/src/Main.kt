fun main() {
    // put your code here
   // 1) the user enters 4 numbers a, b, c, d.
   // 2) The program substitutes them in the appropriate places in the formula a * (x * x * x) + b * (x * x) + c * x + d where x is a value from 0 to 1000 (inclusive) and output to the screen at what value of x the formula is equal to zero

    val a = readln().toInt()
    val b = readln().toInt()
    val c = readln().toInt()
    val d = readln().toInt()

    for(x in 0..1000){
        var formula = a * (x * x * x) + b * (x * x) + c * x + d
        if(formula == 0)
            println(x)
    }
}
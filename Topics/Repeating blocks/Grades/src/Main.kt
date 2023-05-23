fun main() {
    val numberOfStudents = readln().toInt()
    var gradeA = 0
    var gradeB = 0
    var gradeC = 0
    var gradeD = 0

    repeat(numberOfStudents){
        when(readln().toInt()){
            2 -> gradeD++
            3 -> gradeC++
            4 -> gradeB++
            5 -> gradeA++
        }

    }
    print("$gradeD $gradeC $gradeB $gradeA")
}
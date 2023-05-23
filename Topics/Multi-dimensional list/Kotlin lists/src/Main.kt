fun main() {    
    val inputList: MutableList<MutableList<String>> = mutableListOf()
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        val stringsList = readLine()!!.split(' ').toMutableList()
        inputList.add(stringsList)
    }

        println(inputList[2].joinToString())


}
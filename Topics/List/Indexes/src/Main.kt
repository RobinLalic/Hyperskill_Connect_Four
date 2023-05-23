fun solution(products: List<String>, product: String) {
    var index = ""
    for (item in products.indices){
        if (products[item] == product){
            index+= "$item "
        }
    }
    print(index)


}
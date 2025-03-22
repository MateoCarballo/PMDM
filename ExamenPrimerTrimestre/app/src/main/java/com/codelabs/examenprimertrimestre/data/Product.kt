package com.codelabs.examenprimertrimestre.data

data class Product (
    val name: String,
    val quantity: Int = 1,
    val price: Double,
){
    val totalPrice: Double
        get() = price * quantity
}

fun getFakeProducts(size: Int = 10): List<Product>{
return List(size) { getFakeProduct()}
}

fun getFakeProduct(): Product{
    return Product(
        name = products.random(),
        price = (1..10).random().toDouble(),
        quantity = (1..5).random()
    )
}
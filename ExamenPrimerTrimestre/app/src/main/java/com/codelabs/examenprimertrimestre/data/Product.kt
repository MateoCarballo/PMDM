package com.codelabs.examenprimertrimestre.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Product (
    @PrimaryKey val name: String,
    val quantity: Int = 1,
    val price: Double,
){
    val totalPrice: Double
        get() = price * quantity
}

package com.ubayadef.uts_hobby_160421034.model

data class Car(
    val id: Int,
    val title: String,
    val creator: String,
    val images: String,
    val descriptions: ArrayList<String>,
)

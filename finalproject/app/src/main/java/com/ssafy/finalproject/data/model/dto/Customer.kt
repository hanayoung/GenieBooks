package com.ssafy.finalproject.data.model.dto

data class Customer(
    val id: String,
    val nickname: String,
    val pwd: String,
    val age: Int,
    val sex: String,
    val category: List<String>
)
// sex는 WOMAN, MAN
// category는
//FICTION
//COOKING
//SCIENCE
//HEALTH
//HIANDSOC
//TRAVEL
//BUANDEC
//CRANDHOANDFI
//TEANDEN
//RELIGION
//POANDLI
//ARTANDPER
//HOANDHO
//COMPUTER
//HUMAN
//SELFHELF
//POANDSO
//FOREIGN

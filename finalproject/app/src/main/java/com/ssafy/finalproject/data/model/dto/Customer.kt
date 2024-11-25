package com.ssafy.finalproject.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Parcelize
data class Customer(
    val id: String,
    val nickname: String,
    val pwd: String,
    val age: Int,
    val sex: String,
    var category: ArrayList<String>?,
    val fcmToken : String
) : Parcelable {
    var cid : Int = -1
    var point : Int = 0
    constructor(_cid: Int, id: String, nickname: String, pwd: String, age: Int, sex: String, category: ArrayList<String>?, _point: Int, fcmToken: String) : this(id, nickname, pwd, age, sex, category, fcmToken) {
        cid = _cid
        point = _point
    }
}
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

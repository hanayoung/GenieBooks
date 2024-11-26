package com.ssafy.finalproject

enum class Category(private val categoryKr: String) {
    FICTION("소설"),
    COOKING("요리"),
    SCIENCE("과학"),
    HEALTH("건강"),
    HIANDSOC("역사/문화"),
    TRAVEL("여행"),
    CRANDHOANDFI("취미/실용/스포츠"),
    TEANDEN("기술/공학"),
    RELIGION("종교"),
    POANDLI("시/에세이"),
    ARTANDPER("예술/대중문화"),
    COMPUTER("컴퓨터/IT"),
    HUMAN("인문"),
    SELFHELP("자기계발"),
    POANDSO("정치/사회"),
    FOREIGN("외국어"),
    JUVENILE("청소년");

    fun getCategoryKr(): String {
        return categoryKr
    }
}

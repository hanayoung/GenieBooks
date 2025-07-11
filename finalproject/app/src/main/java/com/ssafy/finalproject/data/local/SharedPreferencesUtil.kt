package com.ssafy.finalproject.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ssafy.finalproject.data.model.dto.User

class SharedPreferencesUtil(context: Context) {

    companion object {
        private const val SHARED_PREFERENCES_NAME = "smartstore_preference"
        private const val COOKIES_KEY_NAME = "cookies"
    }

    private var preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    //사용자 정보 저장
    fun addUser(user: User) {
        val editor = preferences.edit()
        editor.putString("id", user.id)
        editor.putString("name", user.name)
        editor.apply()
    }

    fun getUser(): User {
        val id = preferences.getString("id", "")
        if (id != "") {
            val name = preferences.getString("name", "")
            return User(id!!, name!!, "")
        } else {
            return User("", "", "")
        }
    }

    fun deleteUser() {
        //preference 지우기
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    fun addUserCookie(cookies: HashSet<String>) {
        val editor = preferences.edit()
        editor.putStringSet(COOKIES_KEY_NAME, cookies)
        editor.apply()
    }

    fun getUserCookie(): MutableSet<String>? {
        return preferences.getStringSet(COOKIES_KEY_NAME, HashSet())
    }

    fun deleteUserCookie() {
        preferences.edit().remove(COOKIES_KEY_NAME).apply()
    }

    fun addUserId(userId : Int) {
        val editor = preferences.edit()
        editor.putInt("userId", userId)
        editor.apply()
    }

    fun getUserId() : Int {
        return preferences.getInt("userId", -1)
    }

    fun addId(id : String) {
        val editor = preferences.edit()
        editor.putString("id", id)
        editor.apply()
    }

    fun getId() : String {
        return preferences.getString("id","-1") ?: "-1"
    }

    fun addNotice(info: String) {
        val list = getNotice()

        list.add(info)
        val json = Gson().toJson(list)

        preferences.edit().let {
            it.putString("notice", json)
            it.apply()
        }
    }

    fun setNotice(list: MutableList<String>) {
        preferences.edit().let {
            it.putString("notice", Gson().toJson(list))
            it.apply()
        }
    }

    fun getNotice(): MutableList<String> {
        val str = preferences.getString("notice", "")!!
        val list = if (str.isEmpty()) mutableListOf<String>() else Gson().fromJson(
            str,
            MutableList::class.java
        ) as MutableList<String>

        return list
    }

    fun setFcmToken(token : String) {
        val editor = preferences.edit()
        editor.putString("fcm_token", token)
        editor.apply()
    }

    fun getFcmToken() : String {
        return preferences.getString("fcm_token","-1") ?: "-1"
    }

    fun clear() {
        preferences.edit().clear().commit()
    }

}
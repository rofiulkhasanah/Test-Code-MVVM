package com.creartpro.testcodemvvm.data.local.preference

import android.content.Context
import androidx.core.content.edit
import com.creartpro.testcodemvvm.data.entities.User

class UserPreferences(context: Context) {

    companion object{
        private const val USER_PREFS = "user_pref"
        private const val ID = "id"
        private const val USERNAME = "username"
        private const val FULLNAME = "fullname"
        private const val EMAIL = "email"
        private const val PHONE = "phone"
        private const val AVATAR = "avatar"
    }

    private val preferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)

    fun setUser(user: User?) {
        preferences.edit {
            putString(ID, user?.id)
            putString(USERNAME, user?.username)
            putString(FULLNAME, user?.fullname)
            putString(EMAIL, user?.email)
            putString(PHONE, user?.phone)
            putString(AVATAR, user?.avatar)
        }
    }

    fun getUser(): User {
        val user = User()
        user.id = preferences.getString(ID, null)
        user.username = preferences.getString(USERNAME, null)
        user.fullname = preferences.getString(FULLNAME, null)
        user.email = preferences.getString(EMAIL, null)
        user.phone = preferences.getString(PHONE, null)
        user.avatar = preferences.getString(AVATAR, null)
        return user
    }

    fun logout(){
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}
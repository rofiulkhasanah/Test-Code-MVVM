package com.creartpro.testcodemvvm.data.local

import com.creartpro.testcodemvvm.data.entities.User
import com.creartpro.testcodemvvm.data.local.preference.UserPreferences

class UserLocalDataSource(private val preferences: UserPreferences) {

    fun getUser() = preferences.getUser()

    fun setUser(user: User?) = preferences.setUser(user)

    fun getLogout() = preferences.logout()
}
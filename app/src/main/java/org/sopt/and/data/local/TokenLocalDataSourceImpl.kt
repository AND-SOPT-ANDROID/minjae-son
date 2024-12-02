package org.sopt.and.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class TokenLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): TokenLocalDataSource {
    override var token: String
        get() = sharedPreferences.getString(TOKEN, "") ?: ""
        set(value) = sharedPreferences.edit().putString(TOKEN, value).apply()

    override fun removeToken() {
        sharedPreferences.edit().remove(TOKEN).apply()
    }

    companion object {
        private const val TOKEN = "TOKEN"
    }
}
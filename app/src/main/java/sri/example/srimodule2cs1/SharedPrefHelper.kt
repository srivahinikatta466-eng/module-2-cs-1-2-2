package sri.example.srimodule2cs1

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper(context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_REMEMBER_ME = "remember_me"
    }

    fun saveLoginData(name: String, email: String, rememberMe: Boolean) {
        with(sharedPref.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_NAME, name)
            putString(KEY_USER_EMAIL, email)
            putBoolean(KEY_REMEMBER_ME, rememberMe)
            apply()
        }
    }

    fun getSavedEmail(): String {
        return sharedPref.getString(KEY_USER_EMAIL, "") ?: ""
    }

    fun isRememberMe(): Boolean {
        return sharedPref.getBoolean(KEY_REMEMBER_ME, false)
    }

    fun clearLoginData() {
        with(sharedPref.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, false)
            putString(KEY_USER_NAME, "")
            putString(KEY_USER_EMAIL, "")
            putBoolean(KEY_REMEMBER_ME, false)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPref.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}
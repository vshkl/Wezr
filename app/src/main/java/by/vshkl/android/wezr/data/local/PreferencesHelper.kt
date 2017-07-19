package by.vshkl.android.wezr.data.local

import android.content.Context
import android.content.SharedPreferences
import by.vshkl.android.wezr.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper
@Inject constructor(@ApplicationContext val context: Context) {

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        pref.edit().clear().apply()
    }

    companion object {
        val PREF_FILE_NAME = "boilerplate_pref_file"
    }


}

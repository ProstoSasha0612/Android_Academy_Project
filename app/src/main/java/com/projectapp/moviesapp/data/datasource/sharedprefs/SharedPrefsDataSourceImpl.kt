package com.projectapp.moviesapp.data.datasource.sharedprefs

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class SharedPrefsDataSourceImpl(context: Context) : SharedPrefsDataSource {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override suspend fun saveGenresUpdateDate(date: Date) {
        withContext(Dispatchers.IO) {
            val editor = sharedPreferences.edit()
            val currentTime = date.time

            editor.putLong(KEY_UPDATE_TIME, currentTime)
            editor.commit()
        }
    }


    override suspend fun getGenresLastUpdateDate(): Date = withContext(Dispatchers.IO) {
        val timeLong = sharedPreferences.getLong(KEY_UPDATE_TIME, 0)
        Date(timeLong)
    }


    companion object {
        const val SHARED_PREFERENCES_NAME = "Shared Preferences"
        const val KEY_UPDATE_TIME = "key update time"
    }
}
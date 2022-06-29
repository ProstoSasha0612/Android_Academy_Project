package com.projectapp.moviesapp.data.datasource.sharedprefs

import java.util.*

interface SharedPrefsDataSource {

    suspend fun saveGenresUpdateDate(date:Date)

    suspend fun getGenresLastUpdateDate(): Date
}

package com.projectapp.moviesapp.presentation

import android.app.Application
import com.projectapp.moviesapp.data.datasource.local.LocalDataSourceImpl
import com.projectapp.moviesapp.data.datasource.remotedata.RemoteDataSourceImpl
import com.projectapp.moviesapp.data.datasource.sharedprefs.SharedPrefsDataSourceImpl
import com.projectapp.moviesapp.data.repository.MovieRepositoryImpl

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeRepository()
    }

    private fun initializeRepository() {
        val remoteDataSource = RemoteDataSourceImpl()
        val localDataSource = LocalDataSourceImpl(applicationContext)
        val sharedPrefsDataSource = SharedPrefsDataSourceImpl(applicationContext)
        MovieRepositoryImpl.initialise(remoteDataSource, localDataSource, sharedPrefsDataSource)
    }
}

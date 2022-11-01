package com.projectapp.moviesapp.domain.usecases

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.projectapp.moviesapp.data.model.Genre
import java.lang.IllegalArgumentException

object Extra {
    fun getGenresText(genres: List<Genre?>?): String {
        if (genres.isNullOrEmpty()) return ""

        val sb = StringBuilder()
        genres.forEachIndexed { i, genre ->
            sb.append(genre?.name)
            if (i < genres.size - 1) {
                sb.append(", ")
            }
        }
        return sb.toString()
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?: return false
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    }
}

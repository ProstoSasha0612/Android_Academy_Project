package com.projectapp.moviesapp.domain.logic

interface InternetStatusChecker {
    fun isInternetOn(): Boolean
}
package com.projectapp.moviesapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projectapp.moviesapp.R
class NavHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_nav_host, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NavHostFragment().apply {
            }
    }
}
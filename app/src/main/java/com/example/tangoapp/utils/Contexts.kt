package com.example.tangoapp.utils

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tangoapp.R

fun FragmentActivity.getNavController(): NavController {
    return (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
}
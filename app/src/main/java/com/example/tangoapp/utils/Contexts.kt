package com.example.tangoapp.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.tangoapp.R

fun FragmentActivity.getNavController(): NavController {
    return (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
}

fun Context.resolveAttribute(@AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    this.theme?.resolveAttribute(attr, typedValue, true)
    return typedValue.data
}
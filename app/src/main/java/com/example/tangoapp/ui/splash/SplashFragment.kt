package com.example.tangoapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.findNavController
import com.example.tangoapp.R
import com.example.tangoapp.common.BaseFragment

class SplashFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            view.findNavController().navigate(R.id.action_splashFragment_to_newsFragment)
        }, 1750)
    }
}
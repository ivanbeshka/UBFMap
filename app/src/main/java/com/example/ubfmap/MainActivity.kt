package com.example.ubfmap

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //if user already finished hello part
        if (onBoardingFinished()) {
            findNavController(R.id.nav_fragment_enter).navigate(
                R.id.mapFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.mainHelloFragment, true).build()
            )
        }
    }


    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}
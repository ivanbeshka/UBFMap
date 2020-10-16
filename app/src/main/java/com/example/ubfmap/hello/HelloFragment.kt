package com.example.ubfmap.hello

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ubfmap.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class HelloFragment(
    val position: HelloNum
) : Fragment() {

    private lateinit var image: AppCompatImageView
    private lateinit var buttonNext: MaterialButton
    private lateinit var text: MaterialTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hello, container, false)
        initView(root)

        when (position) {
            HelloNum.First -> {
                setBtnNextListeners()
            }
            HelloNum.Second -> {
                setBtnNextListeners()
            }
            HelloNum.Third -> {
                setThirdPositionListeners()
            }
        }

        return root
    }

    private fun setBtnNextListeners() {
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager_first_hello)
        buttonNext.setOnClickListener {
            viewPager?.currentItem = viewPager?.currentItem?.plus(1)!!
        }
    }

    private fun setThirdPositionListeners() {
        buttonNext.setOnClickListener {
            findNavController().navigate(
                R.id.mapFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.mainHelloFragment, true).build()
            )
            onBoardingFinished()
        }
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    private fun initView(root: View) {
        image = root.findViewById(R.id.image_view_hello)
        buttonNext = root.findViewById(R.id.btn_next_hello)
        text = root.findViewById(R.id.text_view_hello)
    }
}
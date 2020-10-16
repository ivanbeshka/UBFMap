package com.example.ubfmap.hello

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.ubfmap.R
import com.rd.PageIndicatorView
import com.rd.animation.type.AnimationType

class MainHelloFragment : Fragment() {

    private lateinit var pagerAdapter: HelloViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerDots: PageIndicatorView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_hello, container, false)
        initView(root)

        val fragments = arrayListOf<Fragment>(
            HelloFragment(HelloNum.First),
            HelloFragment(HelloNum.Second),
            HelloFragment(HelloNum.Third)
        )

        pagerAdapter =
            HelloViewPagerAdapter(
                fragments,
                lifecycle,
                requireActivity().supportFragmentManager
            )
        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                pagerDots.selection = position
            }
        })

        return root
    }

    private fun initView(root: View) {
        viewPager = root.findViewById(R.id.view_pager_first_hello)
        pagerDots = root.findViewById(R.id.page_indicator)
        pagerDots.setAnimationType(AnimationType.DROP)
    }
}
package com.example.novopayassignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.novopayassignment.R
import com.example.novopayassignment.databinding.ActivityMainBinding
import com.example.novopayassignment.model.SourceResponse
import com.example.novopayassignment.viewmodel.NewsViewModel
import com.google.android.material.tabs.TabLayout


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    var newsSourceResponse: SourceResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarText("Top Headines")
        hideBack()
        getNewSourcesApi()
    }

    private fun getNewSourcesApi() {
        val newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.getNewsSourceList().observe(this, Observer { sourceResponse ->
            hideProgress()
            showData()
            setUpViewPager(sourceResponse)
        })
    }

    private fun setUpViewPager(sourceResponse: SourceResponse) {
        newsSourceResponse = sourceResponse
        for (i in 0..9) {
            binding.tabs.addTab(binding.tabs.newTab().setText(sourceResponse.sources[i].name))
        }
        var pagerViewAdapter = SourceViewPagerAdapter(supportFragmentManager, binding.tabs.tabCount)
        binding.viewpager.adapter = pagerViewAdapter
        binding.viewpager.setSwipePagingEnabled(false)
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p: TabLayout.Tab?) {
                if (binding.viewpager != null) {
                    binding.viewpager.currentItem = p!!.position
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }
        })

    }

    fun getNewsSource(position: Int): String? {
        var result: String? = null
        for (i in 0..9) {
            if (i == position) {
                result = newsSourceResponse!!.sources[i].id
            }
        }
        return result
    }

    class SourceViewPagerAdapter(fm: FragmentManager, numberOfTabs: Int) :
        FragmentPagerAdapter(fm) {

        var fragment: Fragment? = null
        var noOfTabs: Int = numberOfTabs

        override fun getItem(position: Int): Fragment {
            for (i in 0..noOfTabs) {
                if (i == position) {
                    fragment = CommonFragmentForSource().getInstance(position);
                    break
                }
            }
            return fragment!!
        }

        override fun getCount(): Int {
            return noOfTabs
        }

    }

}

package com.fexle.upennys.ui.onboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityOnboardBinding
import com.fexle.upennys.ui.onboard.fragments.Onboard1Fragment
import com.fexle.upennys.ui.onboard.fragments.Onboard2Fragment
import com.fexle.upennys.ui.onboard.fragments.Onboard3Fragment
import com.fexle.upennys.utils.getViewModelFactory
import java.util.ArrayList

class OnboardActivity: BaseActivity<ActivityOnboardBinding, OnboardViewModel>() {
    override val layoutId: Int get() = R.layout.activity_onboard
    override val viewModel: OnboardViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager(mBinding.viewPager)
        //userLogin()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Onboard1Fragment())
        adapter.addFragment(Onboard2Fragment())
        adapter.addFragment(Onboard3Fragment())
        viewPager!!.adapter = adapter
        mBinding.viewPagerIndicator.setViewPager(viewPager)
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }

    }
}
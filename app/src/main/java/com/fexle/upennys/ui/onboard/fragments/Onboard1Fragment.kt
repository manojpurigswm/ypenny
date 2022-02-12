package com.fexle.upennys.ui.onboard.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.BaseFragment
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityOnboardBinding
import com.fexle.upennys.databinding.FragmentOnboard1Binding
import com.fexle.upennys.ui.onboard.OnboardActivity
import com.fexle.upennys.ui.onboard.OnboardViewModel
import com.fexle.upennys.utils.getViewModelFactory

class Onboard1Fragment: BaseFragment<FragmentOnboard1Binding, OnboardViewModel>() {
    override val layoutId: Int get() = R.layout.fragment_onboard1
    override val viewModel: OnboardViewModel by viewModels { getViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnSkip.setOnClickListener {
            (requireActivity() as OnboardActivity).mBinding.viewPager.currentItem = 2
        }
        mBinding.btnNext.setOnClickListener {
            (requireActivity() as OnboardActivity).mBinding.viewPager.currentItem = 1
        }
    }
}
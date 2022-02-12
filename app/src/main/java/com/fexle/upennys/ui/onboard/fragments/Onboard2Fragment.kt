package com.fexle.upennys.ui.onboard.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fexle.upennys.BaseFragment
import com.fexle.upennys.R
import com.fexle.upennys.databinding.FragmentOnboard2Binding
import com.fexle.upennys.ui.onboard.OnboardActivity
import com.fexle.upennys.ui.onboard.OnboardViewModel
import com.fexle.upennys.utils.getViewModelFactory

class Onboard2Fragment: BaseFragment<FragmentOnboard2Binding, OnboardViewModel>() {
    override val layoutId: Int get() = R.layout.fragment_onboard2
    override val viewModel: OnboardViewModel by viewModels { getViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnSkip.setOnClickListener {
            (requireActivity() as OnboardActivity).mBinding.viewPager.currentItem = 2
        }

        mBinding.btnNext.setOnClickListener {
            (requireActivity() as OnboardActivity).mBinding.viewPager.currentItem = 2
        }

        mBinding.btnBack.setOnClickListener {
            (requireActivity() as OnboardActivity).mBinding.viewPager.currentItem = 0
        }
    }
}
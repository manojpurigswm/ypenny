package com.fexle.upennys.ui.onboard.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fexle.upennys.BaseFragment
import com.fexle.upennys.R
import com.fexle.upennys.databinding.FragmentOnboard3Binding
import com.fexle.upennys.ui.login.LoginActivity
import com.fexle.upennys.ui.onboard.OnboardActivity
import com.fexle.upennys.ui.onboard.OnboardViewModel
import com.fexle.upennys.ui.signup.Signup1Activity
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class Onboard3Fragment: BaseFragment<FragmentOnboard3Binding, OnboardViewModel>() {
    override val layoutId: Int get() = R.layout.fragment_onboard3
    override val viewModel: OnboardViewModel by viewModels { getViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            (requireActivity() as OnboardActivity).mBinding.viewPager.currentItem = 1
        }

        mBinding.btnLogin.setOnClickListener {
            requireActivity().startActivity<LoginActivity>()
        }

        mBinding.btnSignup.setOnClickListener {
            requireActivity().startActivity<Signup1Activity>()
        }
    }
}
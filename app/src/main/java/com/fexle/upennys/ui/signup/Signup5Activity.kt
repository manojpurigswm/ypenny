package com.fexle.upennys.ui.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivitySignup5Binding
import com.fexle.upennys.ui.home.HomeActivity
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class Signup5Activity: BaseActivity<ActivitySignup5Binding, Signup5ViewModel>() {
    override val layoutId: Int get() = R.layout.activity_signup5
    override val viewModel: Signup5ViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.tilEmail.isHintEnabled = false

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        mBinding.btnNext.setOnClickListener {
            startActivity<HomeActivity>()
        }

    }
}
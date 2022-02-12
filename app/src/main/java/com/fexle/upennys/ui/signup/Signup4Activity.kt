package com.fexle.upennys.ui.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivitySignup4Binding
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class Signup4Activity: BaseActivity<ActivitySignup4Binding, Signup4ViewModel>() {
    override val layoutId: Int get() = R.layout.activity_signup4
    override val viewModel: Signup4ViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }
        mBinding.btnNext.setOnClickListener {
            startActivity<Signup5Activity>()
        }

    }
}
package com.fexle.upennys.ui.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivitySignup1Binding
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class Signup1Activity: BaseActivity<ActivitySignup1Binding, Signup1ViewModel>() {
    override val layoutId: Int get() = R.layout.activity_signup1
    override val viewModel: Signup1ViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.tilEmail.isHintEnabled = false
        mBinding.tilPassword.isHintEnabled = false

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        mBinding.btnNext.setOnClickListener {
            startActivity<Signup2Activity>()
        }

    }
}
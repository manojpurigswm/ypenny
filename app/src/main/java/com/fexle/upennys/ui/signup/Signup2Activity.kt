package com.fexle.upennys.ui.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivitySignup2Binding
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class Signup2Activity: BaseActivity<ActivitySignup2Binding, Signup2ViewModel>() {
    override val layoutId: Int get() = R.layout.activity_signup2
    override val viewModel: Signup2ViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.tilEmail.isHintEnabled = false
        mBinding.tilPassword.isHintEnabled = false
        mBinding.tilRePassword.isHintEnabled = false

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }
        mBinding.btnNext.setOnClickListener {
            startActivity<Signup3Activity>()
        }
    }
}
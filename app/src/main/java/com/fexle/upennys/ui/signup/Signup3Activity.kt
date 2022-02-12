package com.fexle.upennys.ui.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivitySignup3Binding
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class Signup3Activity: BaseActivity<ActivitySignup3Binding, Signup3ViewModel>() {
    override val layoutId: Int get() = R.layout.activity_signup3
    override val viewModel: Signup3ViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.tilHouseNo.isHintEnabled = false
        mBinding.tilStreet.isHintEnabled = false
        mBinding.tilCity.isHintEnabled = false
        mBinding.tilCountry.isHintEnabled = false
        mBinding.tilContactNumber.isHintEnabled = false

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }
        mBinding.btnNext.setOnClickListener {
            startActivity<Signup4Activity>()
        }

    }
}
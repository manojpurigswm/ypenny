package com.fexle.upennys.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityLoginBinding
import com.fexle.upennys.ui.home.HomeActivity
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutId: Int get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.tilEmail.isHintEnabled = false
        mBinding.tilPassword.isHintEnabled = false

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }
        mBinding.btnNext.setOnClickListener {
            startActivity<HomeActivity>()
        }
    }
}
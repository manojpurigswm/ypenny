package com.fexle.upennys.ui.select_bank

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivitySelectBankBinding
import com.fexle.upennys.utils.getViewModelFactory

class SelectBankActivity: BaseActivity<ActivitySelectBankBinding, SelectBankViewModel>() {
    override val layoutId: Int get() = R.layout.activity_select_bank
    override val viewModel: SelectBankViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}
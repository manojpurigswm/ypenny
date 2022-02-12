package com.fexle.upennys.ui.add_card

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityAddCardBinding
import com.fexle.upennys.ui.select_bank.SelectBankActivity
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class AddCardActivity: BaseActivity<ActivityAddCardBinding, AddCardViewModel>() {
    override val layoutId: Int get() = R.layout.activity_add_card
    override val viewModel: AddCardViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.tilEmail.isHintEnabled = false
        mBinding.tilMobile.isHintEnabled = false

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        mBinding.btnNext.setOnClickListener {
            startActivity<SelectBankActivity>()
        }

    }
}
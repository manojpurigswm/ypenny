package com.fexle.upennys.ui.returns

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityReturnBinding
import com.fexle.upennys.utils.getViewModelFactory

class ReturnActivity: BaseActivity<ActivityReturnBinding, ReturnViewModel>() {
    override val layoutId: Int get() = R.layout.activity_return
    override val viewModel: ReturnViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}
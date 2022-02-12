package com.fexle.upennys.ui.change

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityChangeBinding
import com.fexle.upennys.utils.getViewModelFactory

class ChangeActivity: BaseActivity<ActivityChangeBinding, ChangeViewModel>() {
    override val layoutId: Int get() = R.layout.activity_change
    override val viewModel: ChangeViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }


    }
}
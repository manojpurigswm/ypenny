package com.fexle.upennys.ui.in_out

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityInOutBinding
import com.fexle.upennys.utils.getViewModelFactory

class InOutActivity: BaseActivity<ActivityInOutBinding, InOutViewModel>() {
    override val layoutId: Int get() = R.layout.activity_in_out
    override val viewModel: InOutViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }


    }
}
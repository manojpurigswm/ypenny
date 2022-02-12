package com.fexle.upennys.ui.investment

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityInvestmentBinding
import com.fexle.upennys.ui.returns.ReturnActivity
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class InvestmentActivity: BaseActivity<ActivityInvestmentBinding, InvestmentViewModel>() {
    override val layoutId: Int get() = R.layout.activity_investment
    override val viewModel: InvestmentViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        mBinding.btnReturn.setOnClickListener {
            startActivity<ReturnActivity>()
        }

    }
}
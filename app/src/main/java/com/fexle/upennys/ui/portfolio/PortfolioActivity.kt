package com.fexle.upennys.ui.portfolio

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityPortfolioBinding
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class PortfolioActivity: BaseActivity<ActivityPortfolioBinding, PortfolioViewModel>() {
    override val layoutId: Int get() = R.layout.activity_portfolio
    override val viewModel: PortfolioViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        mBinding.layoutHigh.setOnClickListener {
            PortfolioDetailsActivity.type = "high"
            startActivity<PortfolioDetailsActivity>()
        }

        mBinding.layoutMedium.setOnClickListener {
            PortfolioDetailsActivity.type = "mid"
            startActivity<PortfolioDetailsActivity>()
        }

        mBinding.layoutLow.setOnClickListener {
            PortfolioDetailsActivity.type = "low"
            startActivity<PortfolioDetailsActivity>()
        }

    }
}
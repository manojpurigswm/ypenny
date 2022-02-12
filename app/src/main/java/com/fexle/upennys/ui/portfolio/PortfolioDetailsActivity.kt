package com.fexle.upennys.ui.portfolio

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityPortfolioDetailsBinding
import com.fexle.upennys.utils.getViewModelFactory

class PortfolioDetailsActivity : BaseActivity<ActivityPortfolioDetailsBinding, PortfolioViewModel>() {
    override val layoutId: Int get() = R.layout.activity_portfolio_details
    override val viewModel: PortfolioViewModel by viewModels { getViewModelFactory() }

    companion object{
        var type = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        when(type){
            "high" ->{
                mBinding.imageIcon.setImageDrawable(getDrawable(R.drawable.ic_star))
                mBinding.textTitle.text = "Ausgewogen"
                mBinding.textHeading.text = "High profit Details"
            }
            "mid" ->{
                mBinding.imageIcon.setImageDrawable(getDrawable(R.drawable.ic_star_half))
                mBinding.textTitle.text = "Chance"
                mBinding.textHeading.text = "Medium profit Details"
            }
            "low" ->{
                mBinding.imageIcon.setImageDrawable(getDrawable(R.drawable.ic_star_border))
                mBinding.textTitle.text = "Defensiv"
                mBinding.textHeading.text = "Low profit Details"
            }
        }


    }
}
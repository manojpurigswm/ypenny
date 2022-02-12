package com.fexle.upennys.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fexle.upennys.BaseFragment
import com.fexle.upennys.R
import com.fexle.upennys.databinding.FragmentProfileBinding
import com.fexle.upennys.ui.add_money.AddMoneyActivity
import com.fexle.upennys.ui.home.HomeViewModel
import com.fexle.upennys.ui.portfolio.PortfolioActivity
import com.fexle.upennys.ui.transactions.TransactionsActivity
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class ProfileFragment: BaseFragment<FragmentProfileBinding, HomeViewModel>() {
    override val layoutId: Int get() = R.layout.fragment_profile
    override val viewModel: HomeViewModel by viewModels { getViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.layoutChangeAdd.setOnClickListener {  }
        mBinding.layoutPortfolio.setOnClickListener {
            requireActivity().startActivity<PortfolioActivity>()
        }
        mBinding.layoutBankAccount.setOnClickListener {
            requireActivity().startActivity<AddMoneyActivity>()
        }
        mBinding.layoutPaymentType.setOnClickListener {
            requireActivity().startActivity<AddMoneyActivity>()
        }
        mBinding.layoutRecentTransaction.setOnClickListener {
            requireActivity().startActivity<TransactionsActivity>()
        }

        mBinding.btnLogout.setOnClickListener {
            showSessionExpired()
        }

    }

    companion object {

        private var INSTANCE: ProfileFragment? = null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(ProfileFragment::class.java) {
                INSTANCE ?: ProfileFragment()
                    .also { INSTANCE = it }
            }!!


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
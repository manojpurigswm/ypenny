package com.fexle.upennys.ui.transactions

import android.os.Bundle
import androidx.activity.viewModels
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityTransactionsBinding
import com.fexle.upennys.utils.getViewModelFactory

class TransactionsActivity: BaseActivity<ActivityTransactionsBinding, TransactionsViewModel>() {
    override val layoutId: Int get() = R.layout.activity_transactions
    override val viewModel: TransactionsViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}
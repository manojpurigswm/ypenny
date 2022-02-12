package com.fexle.upennys.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fexle.upennys.BaseFragment
import com.fexle.upennys.R
import com.fexle.upennys.databinding.FragmentRewardsBinding
import com.fexle.upennys.ui.home.HomeViewModel
import com.fexle.upennys.utils.getViewModelFactory

class RewardsFragment: BaseFragment<FragmentRewardsBinding, HomeViewModel>() {
    override val layoutId: Int get() = R.layout.fragment_rewards
    override val viewModel: HomeViewModel by viewModels { getViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        private var INSTANCE: RewardsFragment? = null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(RewardsFragment::class.java) {
                INSTANCE ?: RewardsFragment()
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
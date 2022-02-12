package com.fexle.upennys.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.fexle.upennys.BaseFragment
import com.fexle.upennys.R
import com.fexle.upennys.databinding.FragmentHomeBinding
import com.fexle.upennys.ui.home.HomeViewModel
import com.fexle.upennys.utils.getViewModelFactory

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId: Int get() = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels { getViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        private var INSTANCE: HomeFragment? = null

        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(HomeFragment::class.java) {
                INSTANCE ?: HomeFragment()
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
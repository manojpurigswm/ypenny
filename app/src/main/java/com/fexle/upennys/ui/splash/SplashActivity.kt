package com.fexle.upennys.ui.splash

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivitySplashBinding
import com.fexle.upennys.helper.showDebugLog
import com.fexle.upennys.ui.home.HomeActivity
import com.fexle.upennys.ui.onboard.OnboardActivity
import com.fexle.upennys.utils.Status
import com.fexle.upennys.utils.getViewModelFactory

class SplashActivity: BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutId: Int get() = R.layout.activity_splash
    override val viewModel: SplashViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ResourcesCompat.getColor(resources, R.color.colorPrimaryVariant, null)
        }
        mViewModel.isShowHome.observe(this, {
            it?.let {
                if(it){
                    showLogin()
                }
                else{
                    showLogin()
                }
            }
        })
        //userLogin()
    }

    fun showLogin(){
        val intent = Intent(this, OnboardActivity::class.java)
        startActivity(intent)
        onBackPressed()
    }

    fun showHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        onBackPressed()
    }

    /*fun userLogin(){
        val request: LinkedHashMap<String, String> = LinkedHashMap()
        request["page_no"] = "1"
        mViewModel.userLogin(request).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        mViewModel.showProgress.value = false
                        resource.data?.let {
                            showDebugLog(it.message.toString())
                            mViewModel.showToast.value = it.message
                        }
                    }
                    Status.ERROR -> {
                        mViewModel.showProgress.value = false
                    }
                    else -> {

                    }
                }
            }

        })
    }*/
}
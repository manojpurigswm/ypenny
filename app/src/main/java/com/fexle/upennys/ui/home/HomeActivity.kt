package com.fexle.upennys.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityHomeBinding
import com.fexle.upennys.ui.change.ChangeActivity
import com.fexle.upennys.ui.home.fragments.HomeFragment
import com.fexle.upennys.ui.home.fragments.ProfileFragment
import com.fexle.upennys.ui.home.fragments.RewardsFragment
import com.fexle.upennys.ui.in_out.InOutActivity
import com.fexle.upennys.ui.investment.InvestmentActivity
import com.fexle.upennys.ui.portfolio.PortfolioActivity
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override val layoutId: Int get() = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModels { getViewModelFactory() }
    private var pressed = false
    private var TAB = "home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ResourcesCompat.getColor(resources, R.color.colorHeading, null)
        }*/

        setupBottomNavigation()

        mBinding.menuLayout.btnBack.setOnClickListener {
            if (mBinding.drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                mBinding.drawerLayout.closeDrawer(Gravity.RIGHT);
            }
            else {
                mBinding.drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }

        mBinding.menuLayout.layoutAccountSettings.setOnClickListener {
            mBinding.bottomNavigation.selectedItemId = R.id.navigation_profile
        }

        mBinding.menuLayout.layoutInvestment.setOnClickListener {
            startActivity<InvestmentActivity>()
            //mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)
        }

        mBinding.menuLayout.layoutChange.setOnClickListener {
            startActivity<ChangeActivity>()
            //mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)
        }

        mBinding.menuLayout.layoutInOut.setOnClickListener {
            startActivity<InOutActivity>()
            //mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)
        }

        mBinding.menuLayout.layoutPortfolio.setOnClickListener {
            startActivity<PortfolioActivity>()
            //mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)
        }

        mBinding.menuLayout.layoutGraphView.setOnClickListener {

            mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)
        }

        mBinding.menuLayout.layoutChatWithUs.setOnClickListener {

            mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)
        }

        when {
            intent.hasExtra("tab") -> {

                when (intent.extras?.getString("tab")) {
                    "home" -> {
                        mBinding.bottomNavigation.selectedItemId = R.id.navigation_home
                    }
                    "rewards" -> {
                        mBinding.bottomNavigation.selectedItemId = R.id.navigation_rewards
                    }
                    "profile" -> {
                        mBinding.bottomNavigation.selectedItemId = R.id.navigation_profile
                    }
                }
                intent.removeExtra("tab")
            }
            savedInstanceState != null -> when (savedInstanceState.getString("tab")) {
                "home" -> {
                    mBinding.bottomNavigation.selectedItemId = R.id.navigation_home
                }
                "rewards" -> {
                    mBinding.bottomNavigation.selectedItemId = R.id.navigation_rewards
                }
                "profile" -> {
                    mBinding.bottomNavigation.selectedItemId = R.id.navigation_profile
                }
            }
            else -> {
                mBinding.bottomNavigation.selectedItemId = R.id.navigation_home
            }
        }
    }

    private fun setupBottomNavigation() {
        if (mBinding.bottomNavigation == null)
            return
        mBinding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            val intent: Intent
            when (menuItem.itemId) {

                R.id.navigation_home -> {
                    TAB = "home"
                    //supportActionBar!!.title = "Home"
                    loadFragment(HomeFragment.getInstance())
                    mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_rewards -> {
                    TAB = "rewards"
                    //supportActionBar!!.title = "Bookings"
                    loadFragment(RewardsFragment.getInstance())
                    mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    TAB = "profile"
                    //supportActionBar!!.title = "Bookings"
                    loadFragment(ProfileFragment.getInstance())
                    mBinding.drawerLayout.closeDrawer(Gravity.RIGHT)

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_menu -> {
                    if (mBinding.drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                        mBinding.drawerLayout.closeDrawer(Gravity.RIGHT);
                    }
                    else {
                        mBinding.drawerLayout.openDrawer(Gravity.RIGHT);
                    }


                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }

        }
    }

    private fun loadFragment(fragment: Fragment) {
        if (fragment != supportFragmentManager.findFragmentById(R.id.frameContainer)) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameContainer, fragment)
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        if (pressed) {
            super.onBackPressed()
        } else {
            pressed = true
            mViewModel.showToast.value = "Press again to close"
            Handler(Looper.getMainLooper()).postDelayed({
                pressed = false
            }, 3000)
        }
    }
}
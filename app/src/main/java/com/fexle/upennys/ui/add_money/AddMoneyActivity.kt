package com.fexle.upennys.ui.add_money

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fexle.upennys.BaseActivity
import com.fexle.upennys.R
import com.fexle.upennys.databinding.ActivityAddMoneyBinding
import com.fexle.upennys.databinding.LayoutAddMoneyKeyboardBinding
import com.fexle.upennys.databinding.LayoutChooseAccountBinding
import com.fexle.upennys.ui.add_card.AddCardActivity
import com.fexle.upennys.utils.getViewModelFactory
import com.fexle.upennys.utils.startActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddMoneyActivity: BaseActivity<ActivityAddMoneyBinding, AddMoneyViewModel>() {
    override val layoutId: Int get() = R.layout.activity_add_money
    override val viewModel: AddMoneyViewModel by viewModels { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnBack.setOnClickListener {
            onBackPressed()
        }

        setupKeyboard()

        mBinding.btnDone.setOnClickListener { showChooseAccount() }
    }

    fun setupKeyboard(){
        mBinding.btnOne.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "1" }
        mBinding.btnTwo.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "2" }
        mBinding.btnThree.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "3" }
        mBinding.btnFour.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "4" }
        mBinding.btnFive.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "5" }
        mBinding.btnSix.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "6" }
        mBinding.btnSeven.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "7" }
        mBinding.btnEight.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "8" }
        mBinding.btnNine.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "9" }
        mBinding.btnZero.setOnClickListener { mBinding.textAmount.text = mBinding.textAmount.text.toString() + "0" }

        mBinding.btnBackspace.setOnClickListener {
            if(mBinding.textAmount.text.toString().isNotEmpty())
                mBinding.textAmount.text = mBinding.textAmount.text.toString().substring(0, mBinding.textAmount.text.toString().length-1)
        }
    }

    fun showKeyboard(){
        val dialog = BottomSheetDialog(this)
        val layoutBinding: LayoutAddMoneyKeyboardBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_add_money_keyboard, null, false)
        dialog.setContentView(layoutBinding.root)

        dialog.show()
    }

    fun showChooseAccount(){
        val dialog = BottomSheetDialog(this)
        val layoutBinding: LayoutChooseAccountBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_choose_account, null, false)
        dialog.setContentView(layoutBinding.root)

        layoutBinding.btnDropdown.setOnClickListener {
            if(layoutBinding.layoutAirpay.visibility == View.GONE){
                layoutBinding.layoutAirpay.visibility = View.VISIBLE
                layoutBinding.layoutGooglePay.visibility = View.VISIBLE
                layoutBinding.btnDropdown.setImageDrawable(getDrawable(R.drawable.ic_close))
            }
            else{
                layoutBinding.layoutAirpay.visibility = View.GONE
                layoutBinding.layoutGooglePay.visibility = View.GONE
                layoutBinding.btnDropdown.setImageDrawable(getDrawable(R.drawable.ic_arrow_drop_down))
            }

        }

        layoutBinding.layoutAirpay.setOnClickListener {
            layoutBinding.btnDropdown.performClick()
        }
        layoutBinding.layoutGooglePay.setOnClickListener {
            layoutBinding.btnDropdown.performClick()
        }
        layoutBinding.layoutApplePay.setOnClickListener {
            layoutBinding.btnDropdown.performClick()
        }

        layoutBinding.btnAddCard.setOnClickListener {
            startActivity<AddCardActivity>()
        }
        dialog.show()
    }
}
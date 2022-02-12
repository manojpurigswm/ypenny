/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fexle.upennys

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.fexle.upennys.data.source.RemoteDataSource
import com.fexle.upennys.ui.add_card.AddCardViewModel
import com.fexle.upennys.ui.add_money.AddMoneyViewModel
import com.fexle.upennys.ui.change.ChangeViewModel
import com.fexle.upennys.ui.home.HomeViewModel
import com.fexle.upennys.ui.in_out.InOutViewModel
import com.fexle.upennys.ui.investment.InvestmentViewModel
import com.fexle.upennys.ui.login.LoginViewModel
import com.fexle.upennys.ui.onboard.OnboardViewModel
import com.fexle.upennys.ui.portfolio.PortfolioViewModel
import com.fexle.upennys.ui.returns.ReturnViewModel
import com.fexle.upennys.ui.select_bank.SelectBankViewModel
import com.fexle.upennys.ui.signup.*
import com.fexle.upennys.ui.splash.SplashViewModel
import com.fexle.upennys.ui.transactions.TransactionsViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val baseApplication: Application,
    private val remoteDataSource: RemoteDataSource,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(SplashViewModel::class.java) ->
                SplashViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(OnboardViewModel::class.java) ->
                OnboardViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(Signup1ViewModel::class.java) ->
                Signup1ViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(Signup2ViewModel::class.java) ->
                Signup2ViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(Signup3ViewModel::class.java) ->
                Signup3ViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(Signup4ViewModel::class.java) ->
                Signup4ViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(Signup5ViewModel::class.java) ->
                Signup5ViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(InvestmentViewModel::class.java) ->
                InvestmentViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(ReturnViewModel::class.java) ->
                ReturnViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(ChangeViewModel::class.java) ->
                ChangeViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(InOutViewModel::class.java) ->
                InOutViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(PortfolioViewModel::class.java) ->
                PortfolioViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(TransactionsViewModel::class.java) ->
                TransactionsViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(AddMoneyViewModel::class.java) ->
                AddMoneyViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(AddCardViewModel::class.java) ->
                AddCardViewModel(baseApplication, remoteDataSource)
            isAssignableFrom(SelectBankViewModel::class.java) ->
                SelectBankViewModel(baseApplication, remoteDataSource)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}

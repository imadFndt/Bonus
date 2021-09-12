package com.simbirsoft.bonus.presentation.viewmodel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simbirsoft.bonus.domain.interactor.login.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor
) : ViewModel() {

    private companion object {
        const val DELAY = 2000L
    }

    val delay: LiveData<SplashState> get() = delayData
    private val delayData = MutableLiveData<SplashState>(SplashState.Init)

    fun tryLogin() {
        viewModelScope.launch {

            delay(DELAY)

            delayData.value = if (loginInteractor.findSavedUser() != null) {
                SplashState.LoginSuccess
            } else {
                SplashState.LoginFailure
            }
        }
    }
}

sealed class SplashState {
    object Init : SplashState()
    object LoginSuccess : SplashState()
    object LoginFailure : SplashState()
}
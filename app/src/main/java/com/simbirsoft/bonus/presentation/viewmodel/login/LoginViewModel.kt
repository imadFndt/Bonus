package com.simbirsoft.bonus.presentation.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(

) : ViewModel() {

    private companion object {
        const val LOGIN_MAX_LENGTH = 4
        const val PASSWORD_MAX_LENGTH = 4
    }

    private val loginButtonState = MutableLiveData(true) // todo поменять на false
    private val loadingState = MutableLiveData(false)

    var login: String = ""
        set(value) {
            field = value
            validateLoginButton()
        }

    var password: String = ""
        set(value) {
            field = value
            validateLoginButton()
        }

    fun loginButtonState(): LiveData<Boolean> = loginButtonState
    fun loadingState(): LiveData<Boolean> = loadingState

    fun performLogin() {
        viewModelScope.launch {
            loadingState.value = true
            delay(1500L)
            loadingState.value = false
        }
    }

    private fun validateLoginButton() {
        loginButtonState.value = login.length >= LOGIN_MAX_LENGTH &&
                password.length >= PASSWORD_MAX_LENGTH
    }
}
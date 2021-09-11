package com.simbirsoft.bonus.presentation.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(

) : ViewModel() {

    private companion object {
        const val LOGIN_MAX_LENGTH = 4
        const val PASSWORD_MAX_LENGTH = 4
    }

    private val loginButtonState = MutableLiveData(false)

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

    fun performLogin() {

    }

    private fun validateLoginButton() {
        loginButtonState.value = login.length >= LOGIN_MAX_LENGTH &&
                password.length >= PASSWORD_MAX_LENGTH
    }
}
package com.simbirsoft.bonus.presentation.viewmodel.login

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
class LoginViewModel @Inject constructor(

    private val loginInteractor: LoginInteractor

) : ViewModel() {

    private companion object {
        const val LOGIN_MAX_LENGTH = 4
        const val PASSWORD_MAX_LENGTH = 4
    }

    val loginState: LiveData<LoginState> get() = loginStateData
    val loginButtonState: LiveData<Boolean> get() = loginButtonStateData

    private val loginStateData = MutableLiveData<LoginState>()
    private val loginButtonStateData = MutableLiveData(false) // todo поменять на false

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


    fun performLogin() {
        viewModelScope.launch {
            try {

                loginStateData.value = LoginState.Loading
                delay(1200L)
                loginInteractor.login(login, password)
                loginStateData.value = LoginState.Success

            } catch (e: IllegalStateException) {
                loginStateData.value = LoginState.Failure
            }
        }
    }

    fun dismissFailure() {
        if (loginState.value != LoginState.Failure) return
        loginStateData.value = LoginState.Init
    }

    private fun validateLoginButton() {
        loginButtonStateData.value = login.length >= LOGIN_MAX_LENGTH &&
                password.length >= PASSWORD_MAX_LENGTH
    }

}

sealed class LoginState {
    object Init : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    object Failure : LoginState()
}

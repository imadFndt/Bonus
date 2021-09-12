package com.simbirsoft.bonus.presentation.view.login

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.simbirsoft.bonus.MainActivity
import com.simbirsoft.bonus.databinding.ActivityAuthBinding
import com.simbirsoft.bonus.presentation.view.custom.LoaderDialog
import com.simbirsoft.bonus.presentation.viewmodel.login.LoginState
import com.simbirsoft.bonus.presentation.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewModel: LoginViewModel by viewModels()
    private val loadingDialog by lazy { LoaderDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        val transition = Slide().apply {
            slideEdge = Gravity.BOTTOM
        }
        window.enterTransition = transition

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginEditText.addTextChangedListener(LoginTextWatcher {
            viewModel.login = it?.toString().orEmpty()
        })
        binding.passwordEditText.addTextChangedListener(LoginTextWatcher {
            viewModel.password = it?.toString().orEmpty()
        })
        binding.loginButon.setOnClickListener {
            viewModel.performLogin()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginButtonState.observe(this, ::renderLoginButton)
        viewModel.loginState.observe(this, ::handleLogin)
    }

    private fun handleLogin(loginState: LoginState?) {
        loginState ?: return
        when (loginState) {
            LoginState.Init -> Unit
            LoginState.Loading -> showLoading()
            LoginState.Failure -> showFailure()
            LoginState.Success -> showSuccess()
        }
    }

    private fun renderLoginButton(isEnabled: Boolean) {
        binding.loginButon.isEnabled = isEnabled
    }

    private fun showLoading() {
        loadingDialog.show(supportFragmentManager, LoaderDialog.TAG)
    }

    private fun showFailure() {
        loadingDialog.dismiss()
        binding.passwordEditText.setText("")
        Toast.makeText(this, "Не удалось войти", Toast.LENGTH_SHORT).show()
        viewModel.dismissFailure()
    }

    private fun showSuccess() {
        loadingDialog.dismiss()
        startActivity(MainActivity.newInstance(this))
        finish()
    }
}
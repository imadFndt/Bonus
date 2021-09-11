package com.simbirsoft.bonus.presentation.view.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.simbirsoft.bonus.MainActivity
import com.simbirsoft.bonus.databinding.ActivityAuthBinding
import com.simbirsoft.bonus.presentation.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            startActivity(MainActivity.newInstance(this))
            finish()
        }

        viewModel.loginButtonState().observe(this, ::renderLoginButton)
    }

    private fun renderLoginButton(isEnabled: Boolean) {
        binding.loginButon.isEnabled = isEnabled
    }
}
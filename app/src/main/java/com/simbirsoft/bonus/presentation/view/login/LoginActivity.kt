package com.simbirsoft.bonus.presentation.view.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.simbirsoft.bonus.MainActivity
import com.simbirsoft.bonus.databinding.ActivityAuthBinding
import com.simbirsoft.bonus.presentation.view.custom.LoaderDialog
import com.simbirsoft.bonus.presentation.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private val viewModel: LoginViewModel by viewModels()
    private val loadingDialog by lazy { LoaderDialog() }

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
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginButtonState().observe(this, ::renderLoginButton)
        viewModel.loadingState().observe(this, ::renderLoading)
    }

    private fun renderLoginButton(isEnabled: Boolean) {
        binding.loginButon.isEnabled = isEnabled
    }

    private fun renderLoading(isLoading: Boolean) {
        when {
            isLoading && loadingDialog.isAdded.not() -> {
                loadingDialog.show(supportFragmentManager, LoaderDialog.TAG)
            }
            isLoading.not() && loadingDialog.isAdded -> {
                loadingDialog.dismiss()
                startActivity(MainActivity.newInstance(this))
                finish()
            }
            else -> Unit
        }
    }
}
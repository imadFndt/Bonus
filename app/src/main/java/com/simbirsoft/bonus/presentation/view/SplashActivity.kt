package com.simbirsoft.bonus.presentation.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.simbirsoft.bonus.MainActivity
import com.simbirsoft.bonus.databinding.ActivitySplashBinding
import com.simbirsoft.bonus.presentation.view.login.LoginActivity
import com.simbirsoft.bonus.presentation.viewmodel.splash.SplashState
import com.simbirsoft.bonus.presentation.viewmodel.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    companion object {
        const val DELAY = 2000L
    }

    private lateinit var binding: ActivitySplashBinding

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        window.enterTransition = Fade()

        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.delay.observe(this) { state ->
            when (state) {
                SplashState.Init -> viewModel.tryLogin()
                SplashState.LoginSuccess -> toNextScreen<MainActivity>()
                SplashState.LoginFailure -> toNextScreen<LoginActivity>()
            }
        }
    }

    private inline fun <reified T : Activity> toNextScreen() {
        val intent = Intent(this, T::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
        finish()
    }
}
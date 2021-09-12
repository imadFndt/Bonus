package com.simbirsoft.bonus.data.state

import android.content.Context
import androidx.core.content.edit
import com.simbirsoft.bonus.BuildConfig
import com.simbirsoft.bonus.data.entity.UserToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) : Preferences {

    private companion object {
        const val LOGIN_KEY = "login"
        const val PASSWORD_KEY = "password"
    }

    override val token: UserToken?
        get() = sharedPreferences.run {

            val login = getString(LOGIN_KEY, null) ?: return@run null
            val password = getString(PASSWORD_KEY, null) ?: return@run null

            UserToken(login, password)
        }

    private val sharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    override suspend fun setToken(token: UserToken?) = withContext(ioDispatcher) {
        sharedPreferences.edit(commit = true) {
            putString(LOGIN_KEY, token?.login)
            putString(PASSWORD_KEY, token?.password)
        }
    }
}
package com.simbirsoft.bonus.data.repo

import android.content.Context
import com.google.gson.Gson
import com.simbirsoft.bonus.data.entity.UserToken
import com.simbirsoft.bonus.data.ext.fromJson
import com.simbirsoft.bonus.data.state.Preferences
import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses
import com.simbirsoft.bonus.domain.entity.profile.AllUsers
import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.repo.MainRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MockRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
    private val preferences: Preferences,
) : MainRepository {

    private companion object {
        const val BONUSES_PATH = "mock/bonuses.json"
        const val USERS_PATH = "mock/users.json"
    }


    private var bonuses: AllBonuses? = null
    private var users: AllUsers? = null

    override suspend fun getBonuses(): AllBonuses {
        if (bonuses != null) {
            return requireNotNull(bonuses)
        }
        this.bonuses = gson.fromJson(context, BONUSES_PATH)
        return requireNotNull(bonuses)
    }

    override suspend fun getUsers(): AllUsers {
        if (users != null) {
            return requireNotNull(users)
        }
        this.users = gson.fromJson(context, USERS_PATH)
        return requireNotNull(users)
    }

    override suspend fun login(login: String, password: String) {
        getUsers().users.find(findUserCondition(login, password))
            ?.let {

                preferences.setToken(UserToken(login, password))

            } ?: throw IllegalStateException("Login failed")
    }

    override suspend fun getCurrentUser(): User? {
        val token = preferences.token ?: return null

        val users = getUsers().users
        return users.find(findUserCondition(token.login, token.password))
            ?: run {

                preferences.setToken(null)
                throw IllegalStateException("Token expired")
            }
    }

    private fun findUserCondition(login: String, password: String): (User) -> Boolean = { user ->
        user.mail.takeWhile { it != '@' } == login && password == user.pass
    }
}
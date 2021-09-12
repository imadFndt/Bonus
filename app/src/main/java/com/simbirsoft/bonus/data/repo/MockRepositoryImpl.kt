package com.simbirsoft.bonus.data.repo

import android.content.Context
import com.google.gson.Gson
import com.simbirsoft.bonus.data.ext.fromJson
import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses
import com.simbirsoft.bonus.domain.entity.profile.AllUsers
import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.repo.MainRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MockRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
) : MainRepository {

    private companion object {
        const val BONUSES_PATH = "mock/bonuses.json"
        const val USERS_PATH = "mock/users.json"
    }


    private var bonuses: AllBonuses? = null
    private var users: AllUsers? = null
    private var currentUser: User? = null

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
        getUsers().users.find { user ->
            user.mail.takeWhile { it != '@' } == login && password == user.pass
        }?.let {
            currentUser = it
        } ?: throw IllegalStateException("Login failed")
    }

    override suspend fun getCurrentUser(): User {
        return currentUser ?: throw IllegalStateException("No login")
    }
}
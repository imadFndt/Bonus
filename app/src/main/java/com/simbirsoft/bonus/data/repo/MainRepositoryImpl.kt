package com.simbirsoft.bonus.data.repo

import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses
import com.simbirsoft.bonus.domain.entity.profile.AllUsers
import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.repo.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor() : MainRepository {

    override suspend fun getCurrentUser(): User = TODO()

    override suspend fun getBonuses(): AllBonuses {
        return AllBonuses(listOf())
    }

    override suspend fun getUsers(): AllUsers {
        return AllUsers(listOf())
    }

    override suspend fun login(login: String, password: String) = Unit
}
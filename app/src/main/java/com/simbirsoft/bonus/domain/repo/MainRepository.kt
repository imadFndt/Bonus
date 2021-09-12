package com.simbirsoft.bonus.domain.repo

import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses
import com.simbirsoft.bonus.domain.entity.profile.AllUsers
import com.simbirsoft.bonus.domain.entity.profile.User

interface MainRepository {

    suspend fun getBonuses(): AllBonuses

    suspend fun getUsers(): AllUsers

    suspend fun login(login: String, password: String)

    suspend fun getCurrentUser(): User?
}
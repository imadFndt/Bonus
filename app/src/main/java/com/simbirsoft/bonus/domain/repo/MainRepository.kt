package com.simbirsoft.bonus.domain.repo

import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses
import com.simbirsoft.bonus.domain.entity.profile.AllUsers

interface MainRepository {

    suspend fun getBonuses(): AllBonuses

    suspend fun getUsers(): AllUsers
}
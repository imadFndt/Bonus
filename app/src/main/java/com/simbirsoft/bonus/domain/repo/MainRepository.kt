package com.simbirsoft.bonus.domain.repo

import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses

interface MainRepository {

    suspend fun getBonuses(): AllBonuses
}
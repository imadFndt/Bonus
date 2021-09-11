package com.simbirsoft.bonus.domain.repo

import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses
import com.simbirsoft.bonus.domain.entity.profile.Profile

interface MainRepository {

    suspend fun getBonuses(): AllBonuses
}
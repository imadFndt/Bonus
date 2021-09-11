package com.simbirsoft.bonus.data.repo

import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses
import com.simbirsoft.bonus.domain.repo.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(

) : MainRepository {

    override suspend fun getBonuses(): AllBonuses {
        return AllBonuses(listOf())
    }
}
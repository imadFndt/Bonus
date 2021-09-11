package com.simbirsoft.bonus.domain.interactor.bonuses

import com.simbirsoft.bonus.di.MockRepository
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType
import com.simbirsoft.bonus.domain.repo.MainRepository
import javax.inject.Inject

class BonusesInteractorImpl @Inject constructor(
    @MockRepository private val repository: MainRepository,
): BonusesInteractor {

    override suspend fun getBonusesByType(type: BonusType): List<Bonus> {
        return repository.getBonuses()
            .all
            .filter { it.type == type }
    }
}
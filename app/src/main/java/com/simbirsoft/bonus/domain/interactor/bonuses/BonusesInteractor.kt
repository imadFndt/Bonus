package com.simbirsoft.bonus.domain.interactor.bonuses

import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusInfo
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType

interface BonusesInteractor {

    suspend fun getBonusesByType(type: BonusType): List<Bonus>

    suspend fun getBonusInfo(bonus: Bonus): BonusInfo
}
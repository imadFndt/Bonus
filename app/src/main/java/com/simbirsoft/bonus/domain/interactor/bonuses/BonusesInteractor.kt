package com.simbirsoft.bonus.domain.interactor.bonuses

import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusInfo
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType

/**
 * Интерактор для взаимодействия с бонусами на экране плюшек
 */
interface BonusesInteractor {

    /**
     * Получить список бонусов для выбранного типа
     *
     * @param type тип бонусов
     *
     * @return список бонусов с выбранным типом
     */
    suspend fun getBonusesByType(type: BonusType): List<Bonus>

    /**
     * Получить информацию о доступности выбранного бонуса
     *
     * @param bonus выбранный бонус
     *
     * @return информация [BonusInfo] о доступности выбранного бонуса
     */
    suspend fun getBonusInfo(bonus: Bonus): BonusInfo

    /**
     * Получить текст для кнопки экрана деталей по типу бонуса
     *
     * @param type тип бонуса
     *
     * @return текст кнопки
     */
    fun getButtonTitle(type: BonusType): String
}
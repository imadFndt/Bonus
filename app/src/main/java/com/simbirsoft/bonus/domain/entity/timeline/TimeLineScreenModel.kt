package com.simbirsoft.bonus.domain.entity.timeline

/**
 * @param title заголовок тулбара
 * @param countBonus количество доступных годовых бонусов
 * @param lvls данные таймлайна
 */
data class TimeLineScreenModel(
    val title: String,
    val countBonus: Int,
    val lvls: List<TimeLineLevelModel>
)
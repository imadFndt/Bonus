package com.simbirsoft.bonus.domain.interactor.timeline

import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.domain.entity.timeline.TimeLineLevelModel
import com.simbirsoft.bonus.domain.entity.timeline.TimeLineScreenModel
import javax.inject.Inject

class TimelineInteractorImpl @Inject constructor() : TimelineInteractor {

    private fun getTimeLineLevelModels() = listOf(
        TimeLineLevelModel(
            "WelcomeBox",
            "Кружка, блокнот, ручка",
            R.drawable.ic_active_achievement,
            5,
            5
        ),
        TimeLineLevelModel(
            "Футболка",
            "Испытательный срок",
            R.drawable.ic_active_achievement,
            10,
            10
        ),
        TimeLineLevelModel(
            "Кофта",
            "Доклад",
            R.drawable.ic_active_achievement,
            13,
            15
        ),
        TimeLineLevelModel(
            "PowerBank",
            "Менторство",
            R.drawable.ic_active_achievement,
            0,
            20
        ),
        TimeLineLevelModel(
            "Рюкзак",
            "Выступление на Сamp",
            R.drawable.ic_active_achievement,
            0,
            25
        ),
        TimeLineLevelModel(
            "Термос",
            "LvlUp",
            R.drawable.ic_active_achievement,
            0,
            30
        ),
        TimeLineLevelModel(
            "Годовой бонус",
            "1 год стажа",
            R.drawable.ic_unactive_achievement,
            0,
            35
        ),
        TimeLineLevelModel(
            "Годовой бонус",
            "2 года стажа",
            R.drawable.ic_unactive_achievement,
            0,
            40
        ),
        TimeLineLevelModel(
            "Годовой бонус",
            "3 года стажа",
            R.drawable.ic_unactive_achievement,
            0,
            45
        ),
    )

    override fun getTimeLineScreenModels() = TimeLineScreenModel(
        "1 года 4 мес",
        1,
        getTimeLineLevelModels()
    )
}
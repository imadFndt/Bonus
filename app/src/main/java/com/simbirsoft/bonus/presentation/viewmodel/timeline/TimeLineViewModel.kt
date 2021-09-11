package com.simbirsoft.bonus.presentation.viewmodel.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.domain.interactor.timeline.TimelineInteractor
import com.simbirsoft.bonus.presentation.view.timeline.TimeLineLevelModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeLineViewModel @Inject constructor(
    private val interactor: TimelineInteractor
) : ViewModel() {

    private val items = MutableLiveData<List<TimeLineLevelModel>>()

    init {
        items.value = createTimeLineModels()
    }

    fun items(): LiveData<List<TimeLineLevelModel>> = items

    private fun createTimeLineModels() = listOf(
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

}
package com.simbirsoft.bonus.domain.interactor.timeline

import com.simbirsoft.bonus.domain.entity.timeline.TimeLineScreenModel

interface TimelineInteractor {
    suspend fun getTimeLineScreenModels(): TimeLineScreenModel?
}
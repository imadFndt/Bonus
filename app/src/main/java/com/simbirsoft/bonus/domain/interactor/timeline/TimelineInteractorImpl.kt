package com.simbirsoft.bonus.domain.interactor.timeline

import com.simbirsoft.bonus.di.MockRepository
import com.simbirsoft.bonus.domain.entity.timeline.TimeLineScreenModel
import com.simbirsoft.bonus.domain.repo.MainRepository
import javax.inject.Inject

class TimelineInteractorImpl @Inject constructor(
    @MockRepository private val repository: MainRepository
) : TimelineInteractor {

    override suspend fun getTimeLineScreenModels() =
        repository.getCurrentUser().let { user ->
            TimeLineScreenModel(
                user.timework,
                user.countBonus,
                user.timeline
            )
        }
}
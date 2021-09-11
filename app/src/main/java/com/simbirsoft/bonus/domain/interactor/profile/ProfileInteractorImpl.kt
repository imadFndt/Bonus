package com.simbirsoft.bonus.domain.interactor.profile

import com.simbirsoft.bonus.di.MockRepository
import com.simbirsoft.bonus.domain.entity.profile.Achievement
import com.simbirsoft.bonus.domain.entity.profile.Profile
import com.simbirsoft.bonus.domain.repo.MainRepository
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(
    @MockRepository private val repository: MainRepository
) : ProfileInteractor {

    override suspend fun loadProfile(): Profile = Profile(
        name = "Иван иванов",
        avatarUrl = "",
        status = listOf("Ментор", "Собеседующий", "Еще что-то"),
        department = "Mobile Android",
        joinDate = "20.04.2019",
        city = "Ульяновск",
        size = 46,
        achievements = listOf(
            Achievement("Интервью", 13),
            Achievement("Менторство", 7),
            Achievement("Доклады", 7),
            Achievement("Доклады", 7),
            Achievement("Доклады", 7),
        )
    )
}
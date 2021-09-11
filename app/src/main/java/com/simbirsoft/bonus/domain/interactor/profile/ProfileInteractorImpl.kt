package com.simbirsoft.bonus.domain.interactor.profile

import com.simbirsoft.bonus.domain.entity.profile.Achievement
import com.simbirsoft.bonus.domain.entity.profile.Profile
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(

) : ProfileInteractor {

    override fun loadProfile(): Profile = Profile(
        name = "Иван иванов",
        avatarUrl = "",
        status = listOf("Ментор", "Собеседующий", "Еще что-то"),
        department = "Mobile Android",
        about = "TODO",
        achievements = listOf(
            Achievement("", 0),
            Achievement("", 0),
            Achievement("", 0),
            Achievement("", 0),
            Achievement("", 0),
            Achievement("", 0),
            Achievement("", 0),
        )
    )
}
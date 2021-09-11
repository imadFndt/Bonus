package com.simbirsoft.bonus.domain.interactor.profile

import com.simbirsoft.bonus.presentation.view.profile.list.ProfileItem
import com.simbirsoft.bonus.presentation.viewmodel.profile.Profile
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(

) : ProfileInteractor {

    override fun loadProfile() = Profile(
        name = "Иван иванов",
        avatarUrl = "",
        status = listOf("Ментор", "Собеседующий", "Еще что-то"),
        department = "Mobile Android",
        about = "TODO",
        achievements = listOf(
            ProfileItem("", 0),
            ProfileItem("", 0),
            ProfileItem("", 0),
            ProfileItem("", 0),
            ProfileItem("", 0),
            ProfileItem("", 0),
            ProfileItem("", 0),
        )
    )
}
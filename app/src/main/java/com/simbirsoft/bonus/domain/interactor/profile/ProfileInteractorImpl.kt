package com.simbirsoft.bonus.domain.interactor.profile

import com.simbirsoft.bonus.di.MockRepository
import com.simbirsoft.bonus.domain.entity.profile.Achievement
import com.simbirsoft.bonus.domain.entity.profile.Profile
import com.simbirsoft.bonus.domain.repo.MainRepository
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(
    @MockRepository private val repository: MainRepository
) : ProfileInteractor {

    override suspend fun loadProfile(): Profile {
        val user = repository.getUsers().users.first()
        // TODO Маппер
        return Profile(
            name = user.name,

            // TODO определить статусы по достижения
            status = listOf("Ментор", "Собеседующий", "Еще что-то"),
            department = user.department,
            joinDate = user.startwork,
            city = user.city,
            size = user.size,
            achievements = (user.activity + user.merch + user.bonus).map {
                Achievement(it.name, it.count)
            }
        )
    }
}
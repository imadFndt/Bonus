package com.simbirsoft.bonus.presentation.mapper.profile

import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.mapper.Mapper
import javax.inject.Inject

class RolesMapper @Inject constructor() : Mapper<User, MutableList<String>> {
    override fun map(from: User): MutableList<String> {
        return from.activity.mapNotNull {
            when (it.name) {
                "Интервью" -> "Собеседующий"
                "Менторство" -> "Ментор"
                "Доклады" -> "Докладчик"
                else -> null
            }
        }.toMutableList().apply {
            add(0, from.department)
        }
    }
}
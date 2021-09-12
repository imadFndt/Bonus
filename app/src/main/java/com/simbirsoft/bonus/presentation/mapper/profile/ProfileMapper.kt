package com.simbirsoft.bonus.presentation.mapper.profile

import com.simbirsoft.bonus.domain.entity.profile.Item
import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.mapper.Mapper
import com.simbirsoft.bonus.domain.mapper.mapList
import com.simbirsoft.bonus.presentation.model.profile.Achievement
import com.simbirsoft.bonus.presentation.model.profile.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor(
    private val achievementsMapper: Mapper<Item, Achievement>,
    private val rolesMapper: Mapper<User, List<String>>
) : Mapper<User, Profile> {

    override fun map(from: User): Profile {
        return Profile(
            name = from.name,
            roles = rolesMapper.map(from),
            department = from.department,
            joinDate = from.startwork,
            city = from.city,
            size = from.size,
            achievements = (from.activity + from.bonus + from.merch).mapList(achievementsMapper)
        )
    }
}
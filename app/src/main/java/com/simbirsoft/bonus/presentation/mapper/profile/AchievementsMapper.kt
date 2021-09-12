package com.simbirsoft.bonus.presentation.mapper.profile

import com.simbirsoft.bonus.domain.entity.profile.Item
import com.simbirsoft.bonus.domain.mapper.Mapper
import com.simbirsoft.bonus.presentation.mapper.Emoji.emoji
import com.simbirsoft.bonus.presentation.model.profile.Achievement
import javax.inject.Inject

class AchievementsMapper @Inject constructor() : Mapper<Item, Achievement> {
    override fun map(from: Item): Achievement {
        return Achievement(from.name, from.count, from.emoji())
    }
}
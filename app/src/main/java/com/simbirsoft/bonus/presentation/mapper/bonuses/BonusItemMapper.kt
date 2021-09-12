package com.simbirsoft.bonus.presentation.mapper.bonuses

import android.content.Context
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.mapper.Mapper
import com.simbirsoft.bonus.presentation.mapper.bonuses.Emoji.emoji
import com.simbirsoft.bonus.presentation.mapper.bonuses.Emoji.firstLayer
import com.simbirsoft.bonus.presentation.model.bonuses.BonusItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BonusItemMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) : Mapper<Bonus, BonusItem> {

    override fun map(from: Bonus): BonusItem {
        return BonusItem(
            icon = from.firstLayer(context),
            emoji = from.emoji(),
            title = from.title,
            description = from.description(),
            additionalInfo = from.add,
        )
    }

    private fun Bonus.description(): String {
        return add?.let {
            context.resources.getQuantityString(
                R.plurals.tasks_text,
                it.size,
                it.size,
            )
        }.orEmpty()
    }
}
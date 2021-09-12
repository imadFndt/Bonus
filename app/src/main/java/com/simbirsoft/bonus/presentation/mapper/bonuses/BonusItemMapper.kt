package com.simbirsoft.bonus.presentation.mapper.bonuses

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType
import com.simbirsoft.bonus.domain.mapper.Mapper
import com.simbirsoft.bonus.presentation.model.bonuses.BonusItem
import com.simbirsoft.bonus.presentation.viewmodel.bonuses.layerDrawable
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BonusItemMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) : Mapper<Bonus, BonusItem> {

    override fun map(from: Bonus): BonusItem {
        return BonusItem(
            icon = from.icon(),
            title = from.title,
            description = from.description(),
            additionalInfo = from.add,
        )
    }

    private fun Bonus.icon(): Drawable = layerDrawable(
        firstLayer(),
//        secondLayer()
    )

    private fun Bonus.firstLayer(): Drawable {
        val drawable = ContextCompat.getDrawable(
            context,
            R.drawable.bonus_item_icon_low_layer
        ) as GradientDrawable
        val colorResId = when (type) {
            BonusType.MERCH -> R.color.merch_layer_color
            BonusType.ACTIVITY -> R.color.activity_layer_color
            BonusType.BONUS -> R.color.bonus_layer_color
        }
        drawable.color = ContextCompat.getColorStateList(context, colorResId)
        return drawable
    }

    private fun Bonus.secondLayer(): Drawable {
        val resId = R.drawable.ic_progress_timeline
        return ContextCompat.getDrawable(context, resId) ?: error("Drawable not found")
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
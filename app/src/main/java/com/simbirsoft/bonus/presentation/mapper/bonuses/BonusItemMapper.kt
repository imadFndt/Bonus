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
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BonusItemMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) : Mapper<Bonus, BonusItem> {

    override fun map(from: Bonus): BonusItem {
        return BonusItem(
            icon = from.firstLayer(),
            emoji = from.emoji(),
            title = from.title,
            description = from.description(),
            additionalInfo = from.add,
        )
    }

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

    private fun Bonus.description(): String {
        return add?.let {
            context.resources.getQuantityString(
                R.plurals.tasks_text,
                it.size,
                it.size,
            )
        }.orEmpty()
    }

    private fun Bonus.emoji(): String {
        val unicode = when (title) {
            // мерч
            "Welcome Box" -> 0x1F4E6
            "Футболка" -> 0x1F455
            "Книга" -> 0x1F4D5
            "Power Bank" -> 0x1F50B
            "Кофта" -> 0x1F9E5
            "Ежедневник" -> 0x1F4D2
            "Рюкзак" -> 0x1F392
            "Кружка" -> 0x2615
            // активности
            "Выступление на конференции" -> 0x1F399
            "Менторство" -> 0x1F469
            "Выступление в компании" -> 0x1F483
            "Статья для компании" -> 0x1F4C3
            "Статья на Хабре" -> 0x1F4F0
            "Устроиться в Симбирсофт" -> 0x1F973
            "Собеседование" -> 0x2705
            "Пройти испытательный срок" -> 0x1F525
            // бонусы
            "Компенсация техники" -> 0x1F4BB
            "ДМС" -> 0x1F48A
            "Фитнес-клуб" -> 0x1F4AA
            else -> 0x1F60A
        }

        return String(Character.toChars(unicode))
    }
}
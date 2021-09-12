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
            "Welcome Box" -> Emoji.WelcomeBox
            "Футболка" -> Emoji.TShirt
            "Книга" -> Emoji.Book
            "Power Bank" -> Emoji.PowerBank
            "Кофта" -> Emoji.Kofta
            "Ежедневник" -> Emoji.Notebook
            "Рюкзак" -> Emoji.Backpack
            "Кружка" -> Emoji.Cup
            // активности
            "Выступление на конференции" -> Emoji.Conference
            "Менторство" -> Emoji.Teacher
            "Выступление в компании" -> Emoji.CompanyConference
            "Статья для компании" -> Emoji.CompanyArticle
            "Статья на Хабре" -> Emoji.ExternalArticle
            "Устроиться в Симбирсофт" -> Emoji.JoinSimbirsoft
            "Собеседование" -> Emoji.Interview
            "Пройти испытательный срок" -> Emoji.Ispytalka
            // бонусы
            "Компенсация техники" -> Emoji.MoneyForTech
            "ДМС" -> Emoji.DMS
            "Фитнес-клуб" -> Emoji.Fitness
            else -> Emoji.Other
        }

        return String(Character.toChars(unicode))
    }
}
package com.simbirsoft.bonus.presentation.mapper

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType
import com.simbirsoft.bonus.domain.entity.profile.Item

object Emoji {

    const val WelcomeBox = 0x1F4E6
    const val TShirt = 0x1F455
    const val Book = 0x1F4D5
    const val PowerBank = 0x1F50B
    const val Kofta = 0x1F9E5
    const val Notebook = 0x1F4D2
    const val Backpack = 0x1F392
    const val Cup = 0x2615
    const val Conference = 0x1F399
    const val Teacher = 0x1F469
    const val CompanyConference = 0x1F483
    const val CompanyArticle = 0x1F4C3
    const val ExternalArticle = 0x1F4F0
    const val JoinSimbirsoft = 0x1F973
    const val Interview = 0x2705
    const val Ispytalka = 0x1F525
    const val MoneyForTech = 0x1F4BB
    const val DMS = 0x1F48A
    const val Fitness = 0x1F4AA
    const val Other = 0x1F60A

    fun Bonus.firstLayer(context: Context): Drawable {
        val drawable = ContextCompat.getDrawable(
            context,
            R.drawable.bonus_item_icon_low_layer
        ) as GradientDrawable
        val colorResId = when (type) {
            BonusType.MERCH -> R.color.merch_layer_color
            BonusType.ACTIVITY -> R.color.activity_layer_color
            BonusType.BONUS -> com.simbirsoft.bonus.R.color.bonus_layer_color
        }
        drawable.color = ContextCompat.getColorStateList(context, colorResId)
        return drawable
    }

    fun Bonus.emoji(): String {
        return title.findEmoji()
    }

    fun Item.emoji(): String {
        return name.findEmoji()
    }

    private fun String.findEmoji(): String {
        val unicode = when (this) {
            // мерч
            "Welcome Box" -> WelcomeBox
            "Футболка" -> TShirt
            "Книга" -> Book
            "Power Bank" -> PowerBank
            "Кофта" -> Kofta
            "Ежедневник" -> Notebook
            "Рюкзак" -> Backpack
            "Кружка" -> Cup
            // активности
            "Выступление на конференции" -> Conference
            "Менторство" -> Teacher
            "Выступление в компании" -> CompanyConference
            "Статья для компании" -> CompanyArticle
            "Статья на Хабре" -> ExternalArticle
            "Устроиться в Симбирсофт" -> JoinSimbirsoft
            "Собеседование" -> Interview
            "Пройти испытательный срок" -> Ispytalka
            // бонусы
            "Компенсация техники" -> MoneyForTech
            "ДМС" -> DMS
            "Фитнес-клуб" -> Fitness
            else -> Other
        }

        return String(Character.toChars(unicode))
    }
}
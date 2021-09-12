package com.simbirsoft.bonus.domain.interactor.bonuses

import android.content.Context
import com.simbirsoft.bonus.R
import com.simbirsoft.bonus.di.MockRepository
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusInfo
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType
import com.simbirsoft.bonus.domain.entity.profile.User
import com.simbirsoft.bonus.domain.interactor.login.LoginInteractor
import com.simbirsoft.bonus.domain.repo.MainRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BonusesInteractorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @MockRepository private val repository: MainRepository,
    private val loginInteractor: LoginInteractor,
) : BonusesInteractor {

    override fun getButtonTitle(type: BonusType): String {
        val resId = when (type) {
            BonusType.ACTIVITY -> R.string.join_activity_text
            BonusType.MERCH -> R.string.want_this_merch_text
            BonusType.BONUS -> R.string.want_this_bonus_text
        }
        return context.getString(resId)
    }

    override suspend fun getBonusesByType(type: BonusType): List<Bonus> {
        return repository.getBonuses()
            .all
            .filter { it.type == type }
    }

    override suspend fun getBonusInfo(bonus: Bonus): BonusInfo {
        val user = loginInteractor.findSavedUser() ?: error("No user")
        return if (bonus.type == BonusType.BONUS) {
            resolveBonusInfo(user)
        } else {
            successInfo(user, bonus.type)
        }
    }

    private fun resolveBonusInfo(user: User): BonusInfo {
        return when {
            user.yearsAtCompany < 1 -> errorInfo(R.string.incorrect_years_at_company_text)
            user.countBonus < 1 -> errorInfo(R.string.incorrect_available_bonuses_text)
            else -> successInfo(user, BonusType.BONUS)
        }
    }

    private fun errorInfo(textResId: Int): BonusInfo {
        return BonusInfo(
            canGetBonus = false,
            errorText = context.getString(textResId)
        )
    }

    private fun successInfo(user: User, type: BonusType): BonusInfo {
        return BonusInfo(
            canGetBonus = true,
            errorText = context.getString(R.string.bonus_request_sent_text),
            emailText = buildEmailText(user, type)
        )
    }

    private fun buildEmailText(user: User, type: BonusType): String {
        val bonusTextResId = when (type) {
            BonusType.ACTIVITY -> R.string.activity_text
            BonusType.MERCH -> R.string.merch_text
            BonusType.BONUS -> R.string.bonus_text
        }
        return context.getString(R.string.email_body_text)
            .format(user.name, context.getString(bonusTextResId))
    }
}
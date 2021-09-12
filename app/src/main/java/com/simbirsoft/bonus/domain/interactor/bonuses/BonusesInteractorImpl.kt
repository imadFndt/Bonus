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

    override suspend fun getBonusesByType(type: BonusType): List<Bonus> {
        return repository.getBonuses()
            .all
            .filter { it.type == type }
    }

    override suspend fun getBonusInfo(bonus: Bonus): BonusInfo {
        return if (bonus.type == BonusType.BONUS) {
            resolveBonusInfo()
        } else {
            loginInteractor.findSavedUser()?.let {  savedUser ->
                successInfo(savedUser)
            } ?: successInfo()
        }
    }

    private suspend fun resolveBonusInfo(): BonusInfo {
        return loginInteractor.findSavedUser()?.let { savedUser ->
            when {
                savedUser.yearsAtCompany < 1 -> errorInfo(com.simbirsoft.bonus.R.string.incorrect_years_at_company_text)
                savedUser.countBonus < 1 -> errorInfo(com.simbirsoft.bonus.R.string.incorrect_available_bonuses_text)
                else -> successInfo(savedUser)
            }
        } ?: errorInfo(com.simbirsoft.bonus.R.string.error_text)
    }

    private fun errorInfo(textResId: Int): BonusInfo {
        return BonusInfo(
            canGetBonus = false,
            errorText = context.getString(textResId)
        )
    }

    private fun successInfo(user: User? = null): BonusInfo {
        return BonusInfo(
            canGetBonus = true,
            errorText = context.getString(com.simbirsoft.bonus.R.string.bonus_request_sent_text),
            emailText = user?.let {
                buildEmailText(it)
            }.orEmpty()
        )
    }

    private fun buildEmailText(user: User): String {
        return "Пользователь ${user.name} хочет получить бонус "
    }
}
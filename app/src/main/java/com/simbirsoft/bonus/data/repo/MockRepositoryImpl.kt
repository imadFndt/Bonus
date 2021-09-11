package com.simbirsoft.bonus.data.repo

import android.content.Context
import com.google.gson.Gson
import com.simbirsoft.bonus.data.ext.fromJson
import com.simbirsoft.bonus.domain.entity.bonuses.AllBonuses
import com.simbirsoft.bonus.domain.entity.profile.Achievement
import com.simbirsoft.bonus.domain.entity.profile.Profile
import com.simbirsoft.bonus.domain.repo.MainRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MockRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
) : MainRepository {

    private companion object {
        const val BONUSES_PATH = "mock/bonuses.json"
    }

    private var bonuses: AllBonuses? = null

    override suspend fun getBonuses(): AllBonuses {
        if (bonuses != null) {
            return requireNotNull(bonuses)
        }
        this.bonuses = gson.fromJson(context, BONUSES_PATH)
        return requireNotNull(bonuses)
    }
}
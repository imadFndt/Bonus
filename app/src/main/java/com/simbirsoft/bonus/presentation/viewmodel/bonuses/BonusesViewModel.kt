package com.simbirsoft.bonus.presentation.viewmodel.bonuses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType
import com.simbirsoft.bonus.domain.interactor.bonuses.BonusesInteractor
import com.simbirsoft.bonus.domain.mapper.Mapper
import com.simbirsoft.bonus.domain.mapper.mapList
import com.simbirsoft.bonus.presentation.model.bonuses.BonusItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusesViewModel @Inject constructor(
    private val interactor: BonusesInteractor,
    private val bonusMapper: Mapper<Bonus, BonusItem>,
) : ViewModel() {

    private var bonuses: List<Bonus> = emptyList()
    private val defaultType = BonusType.MERCH

    private val itemsState = MutableLiveData<List<BonusItem>>()
    private val selectedTypeState = MutableLiveData(defaultType)

    init {
        loadBonuses(defaultType)
    }

    fun itemsState(): LiveData<List<BonusItem>> = itemsState
    fun selectedTypeState(): LiveData<BonusType> = selectedTypeState

    fun changeSelectedType(type: BonusType) = viewModelScope.launch {
        if (type == selectedTypeState.value) {
            return@launch
        }
        selectedTypeState.value = type
        loadBonuses(type)
    }

    private fun loadBonuses(type: BonusType) = viewModelScope.launch {
        bonuses = interactor.getBonusesByType(type)
        itemsState.value = bonuses.mapList(bonusMapper)
    }

    fun getBonus(item: BonusItem) = bonuses.find { it.title == item.title }
}
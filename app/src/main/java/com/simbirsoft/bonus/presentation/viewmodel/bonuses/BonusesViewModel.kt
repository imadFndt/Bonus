package com.simbirsoft.bonus.presentation.viewmodel.bonuses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusType
import com.simbirsoft.bonus.domain.interactor.bonuses.BonusesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusesViewModel @Inject constructor(
    private val interactor: BonusesInteractor,
) : ViewModel() {

    private var selectedType = BonusType.MERCH
    private val itemsState = MutableLiveData<List<Bonus>>()

    init {
        changeSelectedType(selectedType)
    }

    fun itemsState(): LiveData<List<Bonus>> = itemsState

    fun changeSelectedType(type: BonusType) = viewModelScope.launch {
        selectedType = type
        itemsState.value = interactor.getBonusesByType(type)
    }
}
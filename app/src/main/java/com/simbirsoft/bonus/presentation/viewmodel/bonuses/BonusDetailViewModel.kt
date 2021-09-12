package com.simbirsoft.bonus.presentation.viewmodel.bonuses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simbirsoft.bonus.domain.entity.bonuses.Bonus
import com.simbirsoft.bonus.domain.entity.bonuses.BonusInfo
import com.simbirsoft.bonus.domain.interactor.bonuses.BonusesInteractor
import com.simbirsoft.bonus.presentation.model.bonuses.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BonusDetailViewModel @Inject constructor(
    private val bonusesInteractor: BonusesInteractor,
) : ViewModel() {

    private val successState = MutableLiveData<Event<BonusInfo>>()

    var bonus: Bonus? = null
    var emailText: String = ""

    fun successState(): LiveData<Event<BonusInfo>> = successState

    fun getButtonTitle(): String {
        return bonusesInteractor.getButtonTitle(requireNotNull(bonus).type)
    }

    fun onWantBonusPressed() = viewModelScope.launch {
        val info = bonusesInteractor.getBonusInfo(requireNotNull(bonus))
        successState.value = Event(info)
        emailText
    }
}
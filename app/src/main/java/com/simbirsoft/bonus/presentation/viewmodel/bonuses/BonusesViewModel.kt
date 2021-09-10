package com.simbirsoft.bonus.presentation.viewmodel.bonuses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simbirsoft.bonus.domain.interactor.bonuses.BonusesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BonusesViewModel @Inject constructor(
    private val interactor: BonusesInteractor,
): ViewModel() {

    private val items = MutableLiveData<List<Item>>()

    init {
        items.value = generateItems()
    }

    fun items(): LiveData<List<Item>> = items

    private fun generateItems() = listOf(
        Item("1"),
        Item("2"),
        Item("3"),
        Item("4"),
        Item("5"),
        Item("6"),
        Item("7"),
        Item("8"),
        Item("9"),
        Item("10"),
        Item("11"),
        Item("12"),
        Item("13"),
        Item("14"),
        Item("15"),
        Item("16"),
        Item("17"),
        Item("18"),
        Item("19"),
        Item("20"),
    )
}

data class Item(
    val text: String,
)
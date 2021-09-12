package com.simbirsoft.bonus.presentation.viewmodel.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simbirsoft.bonus.domain.entity.timeline.TimeLineScreenModel
import com.simbirsoft.bonus.domain.interactor.timeline.TimelineInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeLineViewModel @Inject constructor(
    private val interactor: TimelineInteractor
) : ViewModel() {

    private val screenModel = MutableLiveData<TimeLineScreenModel>()

    init {
        loadTimeLineModels()
    }

    fun getScreenModel(): LiveData<TimeLineScreenModel> = screenModel

    private fun loadTimeLineModels() = viewModelScope.launch {
        dispatchTimeLineModels(interactor.getTimeLineScreenModels())
    }

    private fun dispatchTimeLineModels(model: TimeLineScreenModel?) {
        model?.let { screenModel.value = it }
    }
}
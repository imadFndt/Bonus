package com.simbirsoft.bonus.presentation.viewmodel.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simbirsoft.bonus.domain.entity.timeline.TimeLineScreenModel
import com.simbirsoft.bonus.domain.interactor.timeline.TimelineInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeLineViewModel @Inject constructor(
    interactor: TimelineInteractor
) : ViewModel() {

    private val screenModel = MutableLiveData<TimeLineScreenModel>()

    init {
        dispatchTimeLineModels(interactor.getTimeLineScreenModels())
    }

    fun getScreenModel(): LiveData<TimeLineScreenModel> = screenModel

    private fun dispatchTimeLineModels(models: TimeLineScreenModel) {
        screenModel.value = models
    }
}
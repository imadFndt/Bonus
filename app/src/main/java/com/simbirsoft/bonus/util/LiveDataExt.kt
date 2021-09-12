package com.simbirsoft.bonus.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simbirsoft.bonus.presentation.model.bonuses.Event

/**
 * Обзервит [Event] значения, которые выдает [LiveData]
 */
inline fun <T> LiveData<Event<T>>.observeEvent(
    lifecycleOwner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(lifecycleOwner, { it?.getContent()?.let(onEventUnhandledContent) })
}

/**
 * Краткая форма присвоения значения Event<Unit> для [MutableLiveData]
 */
fun MutableLiveData<Event<Unit>>.toggle() {
    value = Event(Unit)
}
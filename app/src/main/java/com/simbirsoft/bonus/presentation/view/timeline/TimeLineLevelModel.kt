package com.simbirsoft.bonus.presentation.view.timeline

/**
 * Модель данных таймлайна
 *
 * @param title заголовок
 * @param description описание
 * @param resIcon индектификатор иконки
 * @param count количество
 * @param maxCount максимальное количество
 */
data class TimeLineLevelModel(
    val title: String,
    val description: String,
    val resIcon: Int,
    val count: Int,
    val maxCount: Int,
) {

    /**
     * метод получения процента заполнения
     */
    fun getPercents() = (count.toDouble() / (maxCount.toDouble() / 100)).toInt()
}
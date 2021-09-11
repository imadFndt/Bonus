package com.simbirsoft.bonus.domain.entity.profile

import com.google.gson.annotations.SerializedName

/**
 * Модель данных таймлайна
 *
 * @param title заголовок
 * @param description описание
 * @param resIcon индектификатор иконки
 * @param count количество
 * @param maxCount максимальное количество
 */
data class Timeline(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val resIcon: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("maxCount")
    val maxCount: Int
) {
    /**
     * метод получения процента заполнения
     */
    fun getPercents() = (count.toDouble() / (maxCount.toDouble() / 100)).toInt()
}
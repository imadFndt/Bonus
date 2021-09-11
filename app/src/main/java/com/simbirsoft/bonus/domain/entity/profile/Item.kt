package com.simbirsoft.bonus.domain.entity.profile

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("name")
    val name: String,
    @SerializedName("count")
    val count: Int
)
package com.simbirsoft.bonus.domain.entity.profile

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("name")
    val name: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("pass")
    val pass: String,
    @SerializedName("countBonus")
    val countBonus: Int,
    @SerializedName("timework")
    val timework: String,
    @SerializedName("yearsAtCompany")
    val yearsAtCompany: Int,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("startwork")
    val startwork: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("department")
    val department: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("activity")
    val activity: List<Item>,
    @SerializedName("merch")
    val merch: List<Item>,
    @SerializedName("bonus")
    val bonus: List<Item>,
    @SerializedName("timeline")
    val timeline: List<Timeline>
)
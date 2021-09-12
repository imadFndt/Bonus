package com.simbirsoft.bonus.domain.mapper

interface Mapper<IN, OUT> {
    fun map(from: IN): OUT
}

fun <IN : Any, OUT> List<IN>.mapList(mapper: Mapper<IN, OUT>): List<OUT> =
    map { mapper.map(it) }
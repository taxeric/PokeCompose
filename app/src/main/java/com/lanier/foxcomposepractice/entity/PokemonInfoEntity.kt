package com.lanier.foxcomposepractice.entity

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/20 15:14
 * Desc  : 合成的info
 */
data class PokemonInfoEntity(
    val base: PokemonBaseInfoEntity ?= null,
    val detail: PokemonDetailInfoEntity ?= null
)
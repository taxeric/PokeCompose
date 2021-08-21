package com.lanier.foxcomposepractice.entity

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/18 13:45
 * Desc  : search result
 */
data class PokemonListEntity(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListData>
)

data class PokemonListData(
    val name: String,
    val url: String
) {

    fun getId(): Int {
        return url.split("/".toRegex()).dropLast(1).last().toInt()
    }

    override fun toString(): String {
        return "ListingData(name='$name', url='$url')"
    }
}

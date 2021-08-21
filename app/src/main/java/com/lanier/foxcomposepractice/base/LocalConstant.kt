package com.lanier.foxcomposepractice.base

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/19 15:29
 * Desc  : 本地常量
 */
class LocalConstant {

    companion object{
        const val LANGUAGE = "zh-Hans"

        //----------gba---------
        fun getGBABackUrl(id: String): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"
        fun getGBABackShinyUrl(id: String): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png"
        fun getGBAForeUrl(id: String): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        fun getGBAForeShinyUrl(id: String): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"
        //----------official-art-pic----------
        fun getOfficialForeUrl(id: String): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}
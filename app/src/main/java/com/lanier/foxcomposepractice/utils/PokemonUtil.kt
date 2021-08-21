package com.lanier.foxcomposepractice.utils

import androidx.compose.ui.graphics.Color

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/20 9:02
 * Desc  :
 */
class PokemonUtil {
    companion object{

        private val COLOR_BLACK = Color(26, 28, 37)
        private val COLOR_BLUE = Color(32, 133, 190)
        private val COLOR_BROWN = Color(163, 127, 92)
        private val COLOR_GRAY = Color(191, 184, 178)
        private val COLOR_GREEN = Color(95, 198, 156)
        private val COLOR_PINK = Color(244, 164, 180)
        private val COLOR_PURPLE = Color(137, 114, 149)
        private val COLOR_RED = Color(199, 84, 74)
        private val COLOR_WHITE = Color(244, 246, 248)
        private val COLOR_YELLOW = Color(224, 215, 112)

        fun switchPokemonBaseColor(colorName: String): Color{
            return when(colorName){
                "black"      -> COLOR_BLACK
                "blue"       -> COLOR_BLUE
                "brown"      -> COLOR_BROWN
                "gray"       -> COLOR_GRAY
                "green"      -> COLOR_GREEN
                "pink"       -> COLOR_PINK
                "purple"     -> COLOR_PURPLE
                "red"        -> COLOR_RED
                "white"      -> COLOR_WHITE
                "yellow"     -> COLOR_YELLOW
                else         -> Color.Transparent
            }
        }

        fun switchPokemonType(type: String): String{
            return when(type){
                "normal"     -> "一般"
                "fighting"   -> "格斗"
                "flying"     -> "飞行"
                "poison"     -> "毒"
                "ground"     -> "地面"
                "rock"       -> "岩石"
                "bug"        -> "虫"
                "ghost"      -> "幽灵"
                "steel"      -> "钢"
                "fire"       -> "火"
                "water"      -> "水"
                "grass"      -> "草"
                "electric"   -> "电"
                "psychic"    -> "超能"
                "ice"        -> "冰"
                "dragon"     -> "龙"
                "dark"       -> "恶"
                "fairy"      -> "妖精"
                else         -> "未知"
            }
        }

        fun switchPokemonTypeColor(type: String): Color{
            return when(type){
                "一般"        -> Color(143,152,159)
                "格斗"        -> Color(217,54,106)
                "飞行"        -> Color(137,167,218)
                "毒"          -> Color(175,103,195)
                "地面"        -> Color(226,116,75)
                "岩石"        -> Color(198,182,141)
                "虫"          -> Color(133,192,65)
                "幽灵"        -> Color(79,105,168)
                "钢"          -> Color(78,141,159)
                "火"          -> Color(254,152,93)
                "水"          -> Color(63,144,209)
                "草"          -> Color(68,188,97)
                "电"          -> Color(247,208,80)
                "超能"        -> Color(254,107,122)
                "冰"          -> Color(89,207,190)
                "龙"          -> Color(0,110,189)
                "恶"          -> Color(91,83,100)
                "妖精"        -> Color(245,138,226)
                else         -> Color(60,105,94)
            }
        }

        fun switchPokemonEggGroup(type: String): String{
            return when(type){
                "monster"        -> "怪兽"
                "water1"         -> "水1"
                "bug"            -> "虫"
                "flying"         -> "飞行"
                "ground"         -> "陆上"
                "fairy"          -> "妖精"
                "plant"          -> "植物"
                "humanshape"     -> "人型"
                "water3"         -> "水3"
                "mineral"        -> "矿物"
                "indeterminate"  -> "不定形"
                "water2"         -> "水2"
                "ditto"          -> "同上"
                "dragon"         -> "龙"
                "no-eggs"        -> "未知分组"
                else             -> "未知"
            }
        }
    }
}
package com.lanier.foxcomposepractice.entity

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/18 13:52
 * Desc  : detail entity
 */
data class PokemonDetailInfoEntity(
    val base_happiness: Int = 0,//基础亲密度
    val capture_rate: Int = 0,//捕获概率
    val color: PokemonColor ?= null,//颜色
    val egg_groups: List<EggGroup> ?= null,//蛋组
    val evolution_chain: EvolutionChain ?= null,
    val evolves_from_species: Any ?= null,
    val flavor_text_entries: List<FlavorTextEntry> ?= null,//描述文本
    val form_descriptions: List<Any> ?= null,
    val forms_switchable: Boolean = false,
    val gender_rate: Int = 0,//性别比例，-1，1
    val genera: List<Genera> ?= null,//类别
    val generation: Generation ?= null,
    val growth_rate: GrowthRate ?= null,
    val habitat: Habitat ?= null,
    val has_gender_differences: Boolean = false,
    val hatch_counter: Int = 0,
    val id: Int = 0,
    val is_baby: Boolean = false,
    val is_legendary: Boolean = false,
    val is_mythical: Boolean = false,
    val name: String = "",
    val names: List<Name> ?= null,
    val order: Int = 0,
    val pal_park_encounters: List<PalParkEncounter> ?= null,
    val pokedex_numbers: List<PokedexNumber> ?= null,
    val shape: Shape ?= null,
    val varieties: List<Variety> ?= null
)

data class PokemonColor(
    val name: String,
    val url: String
)

data class EggGroup(
    val name: String,
    val url: String
)

data class EvolutionChain(
    val url: String
)

data class FlavorTextEntry(
    val flavor_text: String,//描述
    val language: Language,//固定在 zh-Hans
    val version: VersionDetails//版本
)

data class Genera(
    val genus: String,
    val language: LanguageX//根据这个值来判断类别，固定在zh-Hans
)

data class Generation(
    val name: String,
    val url: String
)

data class GrowthRate(
    val name: String,
    val url: String
)

data class Habitat(
    val name: String,
    val url: String
)

data class Name(
    val language: LanguageXX,//固定在 zh-Hans
    val name: String//精灵名称
)

data class PalParkEncounter(
    val area: Area,
    val base_score: Int,
    val rate: Int
)

data class PokedexNumber(
    val entry_number: Int,
    val pokedex: Pokedex
)

data class Shape(
    val name: String,
    val url: String
)

data class Variety(
    val is_default: Boolean,
    val pokemon: Pokemon
)

data class Language(
    val name: String,
    val url: String
)

data class VersionDetails(
    val name: String,//版本名称
    val url: String
)

data class LanguageX(
    val name: String,
    val url: String
)

data class LanguageXX(
    val name: String,
    val url: String
)

data class Area(
    val name: String,
    val url: String
)

data class Pokedex(
    val name: String,
    val url: String
)

data class Pokemon(
    val name: String,
    val url: String
)
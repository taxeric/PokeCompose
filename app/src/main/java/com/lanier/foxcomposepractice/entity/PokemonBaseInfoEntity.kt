package com.lanier.foxcomposepractice.entity

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/18 13:51
 * Desc  : base info
 */
data class PokemonBaseInfoEntity(
    val abilities: List<Ability> ?= null,
    val base_experience: Int = 0,
    val forms: List<Form> ?= null,
    val game_indices: List<GameIndice> ?= null,
    val height: Int = 0,
    val held_items: List<Any> ?= null,
    val id: Int = 0,
    val is_default: Boolean = false,
    val location_area_encounters: String = "",
    val moves: List<Move> ?= null,
    val name: String = "",
    val order: Int = 0,
    val species: Species ?= null,
    val sprites: Sprites ?= null,
    val stats: List<Stat> ?= null,
    val types: List<Type> ?= null,
    val weight: Int = 0
)

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)

data class Form(
    val name: String,
    val url: String
)

data class GameIndice(
    val game_index: Int,
    val version: Version
)

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)

data class Species(
    val name: String,
    val url: String
)

data class Sprites(
    val back_default: String,
    val back_female: Any,
    val back_shiny: String,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any,
    val other: Other,
    val versions: Versions
)

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatX
)

data class Type(
    val slot: Int,
    val type: TypeX
)

data class AbilityX(
    val name: String,
    val url: String
)

data class Version(
    val name: String,
    val url: String
)

data class MoveX(
    val name: String,
    val url: String
)

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup
)

data class MoveLearnMethod(
    val name: String,
    val url: String
)

data class VersionGroup(
    val name: String,
    val url: String
)

data class Other(
    val dream_world: DreamWorld,
    val official_artwork: OfficialArtwork
)

data class Versions(
    val generation_i: GenerationI,
    val generation_ii: GenerationIi,
    val generation_iii: GenerationIii,
    val generation_iv: GenerationIv,
    val generation_v: GenerationV,
    val generation_vi: GenerationVi,
    val generation_vii: GenerationVii,
    val generation_viii: GenerationViii
)

data class DreamWorld(
    val front_default: String,
    val front_female: Any
)

data class OfficialArtwork(
    val front_default: String
)

data class GenerationI(
    val red_blue: RedBlue,
    val yellow: Yellow
)

data class GenerationIi(
    val crystal: Crystal,
    val gold: Gold,
    val silver: Silver
)

data class GenerationIii(
    val emerald: Emerald,
    val firered_leafgreen: FireredLeafgreen,
    val ruby_sapphire: RubySapphire
)

data class GenerationIv(
    val diamond_pearl: DiamondPearl,
    val heartgold_soulsilver: HeartgoldSoulsilver,
    val platinum: Platinum
)

data class GenerationV(
    val black_white: BlackWhite
)

data class GenerationVi(
    val omegaruby_alphasapphire: OmegarubyAlphasapphire,
    val x_y: XY
)

data class GenerationVii(
    val icons: Icons,
    val ultra_sun_ultra_moon: UltraSunUltraMoon
)

data class GenerationViii(
    val icons: IconsX
)

data class RedBlue(
    val back_default: String,
    val back_gray: String,
    val front_default: String,
    val front_gray: String
)

data class Yellow(
    val back_default: String,
    val back_gray: String,
    val front_default: String,
    val front_gray: String
)

data class Crystal(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
)

data class Gold(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
)

data class Silver(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
)

data class Emerald(
    val front_default: String,
    val front_shiny: String
)

data class FireredLeafgreen(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
)

data class RubySapphire(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
)

data class DiamondPearl(
    val back_default: String,
    val back_female: Any,
    val back_shiny: String,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)

data class HeartgoldSoulsilver(
    val back_default: String,
    val back_female: Any,
    val back_shiny: String,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)

data class Platinum(
    val back_default: String,
    val back_female: Any,
    val back_shiny: String,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)

data class BlackWhite(
    val animated: Animated,
    val back_default: String,
    val back_female: Any,
    val back_shiny: String,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)

data class Animated(
    val back_default: String,
    val back_female: Any,
    val back_shiny: String,
    val back_shiny_female: Any,
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)

data class OmegarubyAlphasapphire(
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)

data class XY(
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)

data class Icons(
    val front_default: String,
    val front_female: Any
)

data class UltraSunUltraMoon(
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
)

data class IconsX(
    val front_default: String,
    val front_female: Any
)

data class StatX(
    val name: String,
    val url: String
)

data class TypeX(
    val name: String,
    val url: String
)

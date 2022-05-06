package com.lanier.foxcomposepractice.api

import com.lanier.foxcomposepractice.entity.DataX
import com.lanier.foxcomposepractice.entity.PokemonBaseInfoEntity
import com.lanier.foxcomposepractice.entity.PokemonDetailInfoEntity
import com.lanier.foxcomposepractice.entity.PokemonListEntity
import com.lanier.libbase.net.HttpBaseBean
import retrofit2.http.*

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 15:20
 * Desc  : 接口
 */
interface OpenApi {

    @FormUrlEncoded
    @POST("checkVersion")
    suspend fun testFunction(
        @Field("lvc") versionCode: Int,
        @Field("pn") pn: String,
        @Field("os") os: String
    ): HttpBaseBean<DataX>

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonListEntity

    /**
     * 该接口可获得：
     * 基础亲密度
     * 基础颜色
     * 名字
     * 蛋组
     * 性别比例
     * 类别
     * 捕获率
     */
    @GET("pokemon-species/{path}/")
    suspend fun searchPokemonDetailInfo(
        @Path("path") baseName_or_id: String
    ): PokemonDetailInfoEntity

    /**
     * 该接口可获得：
     * id
     * 用于查询的名字
     * 高度
     * 重量
     * 属性
     */
    @GET("pokemon/{path}/")
    suspend fun describeInfo(
        @Path("path") baseName_or_id: String
    ): PokemonBaseInfoEntity

    /**
     * 特性
     */
    @GET("ability/{path}")
    suspend fun getPokemonAbility(
        @Path("path") id_or_name: String
    )
}
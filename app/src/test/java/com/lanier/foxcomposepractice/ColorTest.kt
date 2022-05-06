package com.lanier.foxcomposepractice

import android.graphics.BitmapFactory
import android.graphics.Color
import com.lanier.libbase.utils.logE
import com.lanier.libbase.utils.logI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import java.lang.Exception
import java.net.URL
import java.util.*
import kotlin.random.Random

/**
 * Create by Eric
 * on 2022/5/6
 */
@Test
fun main() = runBlocking {
    val random = Random.nextInt(897) + 1
    val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${random}.png"
    "current -> $random".logI()
    "load -> $imgUrl".logI()
    val startTime = System.currentTimeMillis()
    val colors = getPixel("https://imgsa.baidu.com/forum/pic/item/a8014c086e061d95ee6432d677f40ad162d9ca3a.jpg")
    val mostColor = getMostFoundColor(colors) {
        it.keys.forEach { key ->
            println("$key : ${it[key]}")
        }
    }
    val endTime = System.currentTimeMillis()
    println("the most found color is -> $mostColor, and cost time = ${endTime - startTime}")
}

suspend fun getPixel(url: String): List<Int>{
    val rgb = arrayListOf<Int>()
    val bitmap = try {
//        withContext(Dispatchers.Default) {
            val httpConnection = URL(url).openConnection().apply {
                connectTimeout = 10 * 1_000
            }
            httpConnection.connect()
            val stream = httpConnection.getInputStream()
            val b = BitmapFactory.decodeStream(stream)
            stream.close()
            b
//        }
    } catch (e: Exception){
        "get bitmap failed -> ${e.message}".logE()
        null
    } ?: return rgb
    val bitmapWidth = bitmap.width
    val bitmapHeight = bitmap.height
    val pixels = IntArray(bitmapWidth * bitmapHeight)
    bitmap.getPixels(pixels, 0, bitmapWidth, 0, 0, bitmapWidth, bitmapHeight)
    repeat(pixels.size){
        val clr = pixels[it]
        val r = (clr and 0x00ff0000) shr 16
        val g = (clr and 0x0000ff00) shr 8
        val b = (clr and 0x000000ff)
        val color = Color.rgb(r, g, b)
        if (color != Color.TRANSPARENT) {
            rgb.add(color)
        }
    }
    withContext(Dispatchers.Default) {}
    return rgb
}

suspend fun getMostFoundColor(colors: List<Int>, action: (map: TreeMap<Int, Int>) -> Unit = {}): Int {
    if (colors.isEmpty()){
        "no colors".logE()
        return -1
    }
    val treeMap = TreeMap<Int, Int>()
    withContext(Dispatchers.IO) {
        repeat(colors.size) {
            if (treeMap.containsKey(it)) {
                treeMap[it] = treeMap[it]!! + 1
            } else {
                treeMap[it] = 1
            }
        }
    }
    withContext(Dispatchers.Default) {
        action(treeMap)
    }
    return treeMap.firstKey()
}

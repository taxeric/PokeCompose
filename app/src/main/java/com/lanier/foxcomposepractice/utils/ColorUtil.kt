package com.lanier.foxcomposepractice.utils

import android.graphics.BitmapFactory
import android.graphics.Color
import com.lanier.libbase.utils.logE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.URL
import java.util.*

/**
 * Create by Eric
 * on 2022/5/6
 */
object ColorUtil {

    suspend fun getPixel(url: String): List<Int>{
        val rgb = arrayListOf<Int>()
        val bitmap = try {
            withContext(Dispatchers.Default) {
                val httpConnection = URL(url).openConnection().apply {
                    connectTimeout = 10 * 1_000
                }
                httpConnection.connect()
                val stream = httpConnection.getInputStream()
                val b = BitmapFactory.decodeStream(stream)
                stream.close()
                b
            }
        } catch (e: Exception){
            "get bitmap failed -> ${e.message}".logE()
            null
        } ?: return rgb
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        val pixels = IntArray((bitmapWidth - 100) * (bitmapHeight - 100))
        bitmap.getPixels(pixels, 0, bitmapWidth, 100, 100, bitmapWidth - 100, bitmapHeight - 100)
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
        withContext(Dispatchers.Main) {}
        return rgb
    }

    suspend fun getMostFoundColor(colors: List<Int>, action: (map: TreeMap<Int, Int>) -> Unit = {}): Int {
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
        withContext(Dispatchers.Main) {
            action(treeMap)
        }
        return treeMap.firstKey()
    }
}
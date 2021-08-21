package com.lanier.foxcomposepractice.utils

import com.google.gson.Gson
import com.lanier.libbase.utils.LogUtil
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
 * Create by Eric
 * on 2021/8/21
 */
object FileUtil {

    var SAVE_PATH = ""
    val prefix = "localPokeList_page_"

    private val gson = Gson()

    fun read(fileName: String): String = read(SAVE_PATH, fileName)

    fun write(fileName: String, obj: Any): Boolean = write(SAVE_PATH, fileName, obj)

    fun <A> convert(str: String, clazz: Class<A>): A = gson.fromJson(str, clazz)

    fun read(path: String, fileName: String): String{
        if (path.isEmpty()){
            return ""
        }
        var string = ""
        val file = File(path + fileName)
        if (!file.exists()){
            return string
        }
        try {
            val reader = FileReader(path + fileName)
            val bufferedReader = BufferedReader(reader)
            string = bufferedReader.readText()
            reader.close()
            bufferedReader.close()
        } catch (e: Exception){
            LogUtil.e("read failed: ${e.message}")
        }
        return string
    }

    fun write(path: String, fileName: String, obj: Any): Boolean{
        if (path.isEmpty()){
            return false
        }
        val gson = Gson()
        val value = gson.toJson(obj)
        try {
            File(path + fileName).writeText(value)
            return true
        } catch (e: Exception){
            LogUtil.e("write failed: ${e.message}")
        }
        return false
    }
}
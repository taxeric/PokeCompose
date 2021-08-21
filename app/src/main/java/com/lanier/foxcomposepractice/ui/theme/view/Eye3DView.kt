package com.lanier.foxcomposepractice.ui.theme.view

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_NORMAL
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import com.lanier.foxcomposepractice.R
import kotlin.math.abs

/**
 * Author: 芒硝
 * Email : 1248389474@qq.com
 * Date  : 2021/8/17 10:51
 * Desc  : 裸眼三d
 */

private val NS2S = 1f / 1000000000f
private var timeStamp: Long = 0
private val MAX_TRANSLATE_ANGLE = 80.0
private val MAX_OFFSET = 3000

@Composable
fun Eye3DMain(width: Int){
    //动态标记
    var xDistance by remember {
        mutableStateOf(0f)
    }
    var yDistance by remember {
        mutableStateOf(0f)
    }

    //图片
    val picBack = ImageBitmap.imageResource(id = R.drawable.back)
    val picMidd = ImageBitmap.imageResource(id = R.drawable.midd)
    val picFore = ImageBitmap.imageResource(id = R.drawable.fore)

    //获取陀螺仪传感器
    val context = LocalContext.current
    val sensorManager: SensorManager ?= getSystemService(context, SensorManager::class.java)
    val sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    logI("陀螺仪传感器？$sensor")

    sensorManager?.registerListener(object: SensorEventListener{
        override fun onSensorChanged(event: SensorEvent?) {
            if (timeStamp != 0L) {
                //获取xyz三轴角速度
                event?.let {
                    val fx = it.values[0]
                    val fy = it.values[1]
                    val fz = it.values[2]

                    logI("初始：$fx  $fy  $fz")

                    var angularX = 0.0
                    var angularY = 0.0
                    var angularZ = 0.0

                    val x = abs(fx)
                    val y = abs(fy)
                    val z = abs(fz)

                    val dt = (it.timestamp - timeStamp) * NS2S
                    angularX += (fx * dt).toDouble()
                    angularY += (fy * dt).toDouble()
                    angularZ += (fz * dt).toDouble()

                    logI("计算：$angularX  $angularY  $dt")

                    //设置最大值
                    if (angularX > MAX_TRANSLATE_ANGLE){
                        angularX = MAX_TRANSLATE_ANGLE
                    } else {
                        if (angularX < - MAX_TRANSLATE_ANGLE){
                            angularX = - MAX_TRANSLATE_ANGLE
                        }
                    }
                    if (angularY > MAX_TRANSLATE_ANGLE){
                        angularY = MAX_TRANSLATE_ANGLE
                    } else {
                        if (angularY < - MAX_TRANSLATE_ANGLE){
                            angularY = - MAX_TRANSLATE_ANGLE
                        }
                    }

                    val xRadio: Float = (angularY / MAX_TRANSLATE_ANGLE).toFloat()
                    val yRadio: Float = (angularX / MAX_TRANSLATE_ANGLE).toFloat()

                    logI("设置：$angularX  $angularY  $xRadio  $yRadio")

                    if (x > y + z) {
                        xDistance = 0f
                        yDistance = yRadio * MAX_OFFSET
//                        yDistance = Random.nextInt(10).toFloat()
                    } else if (y > x + z) {
                        xDistance = xRadio * MAX_OFFSET
//                        xDistance = Random.nextInt(10).toFloat()
                        yDistance = 0f
                    }

                    logI("最终：$xDistance  $yDistance")
                }
            }
            timeStamp = event?.timestamp!!
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }
    }, sensor, SENSOR_DELAY_NORMAL)
    val middleColor = Color(0x5097B0B8)
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .background(middleColor)
        .height(200.dp)){
        //背景移动
        translate(- xDistance, - yDistance) {
            logI("重新绘制back")
//            drawCircle(Color.Red, radius = 100f, style = Stroke(2f))
            drawImage(picBack)
        }
        //中层不动
//        drawLine(color = Color.Blue, Offset(0f, 0f), Offset(200f, 200f))
        drawImage(picMidd)
        //前景移动，为背景的反方向
        translate(xDistance, yDistance) {
            logI("重新绘制fore")
//            drawCircle(Color.Yellow, radius = 50f, style = Fill)
            drawImage(picFore)
        }
    }
}

fun logI(msg: String){
    Log.i("LANIER_TAG", msg)
}
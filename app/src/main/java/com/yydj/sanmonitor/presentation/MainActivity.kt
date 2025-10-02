/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.yydj.sanmonitor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.tooling.preview.devices.WearDevices
import com.yydj.sanmonitor.presentation.theme.SanMonitorTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)
        setContent {
            SanMonitorApp()
        }
    }
}

@Composable
fun SanMonitorApp() {
    SanMonitorTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            // 背景元素
            TimeText(
                modifier = Modifier.align(Alignment.TopCenter),
                timeTextStyle = TextStyle(color = Color.Gray)
            )
            
            // 主界面内容
            SanMonitorContent(
                statusValue = 17,
                statusTime = "25分钟前",
                statusMessage = "适当放松"
            )
        }
    }
}

@Composable
fun SanMonitorContent(
    statusValue: Int,
    statusTime: String,
    statusMessage: String
) {
    // 计算状态值对应的颜色和表情
    val statusColor = when {
        statusValue >= 70 -> Color.Green
        statusValue >= 40 -> Color.Yellow
        statusValue >= 20 -> Color(0xFFFFA500) // Orange
        else -> Color.Red
    }
    
    val faceEmoji = when {
        statusValue >= 70 -> "😊"
        statusValue >= 40 -> "😐"
        statusValue >= 20 -> "😕"
        else -> "😟"
    }
    
    // 计算进度条的动画值
    val animatedProgress by animateFloatAsState(
        targetValue = statusValue / 100f,
        animationSpec = tween(durationMillis = 1000)
    )
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // 圆形进度条
        CircularProgressIndicator(
            progress = animatedProgress,
            strokeWidth = 10.dp,
            modifier = Modifier.size(180.dp)
        )
        
        // 中心表情图标
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.Red)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(text = faceEmoji, fontSize = 40.sp, color = Color.White)
        }
        
        // 状态消息
        Text(
            text = statusMessage,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 70.dp)
        )
        
        // 状态值
        Text(
            text = "状态值 $statusValue",
            color = statusColor,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        )
        
        // 时间信息
        Text(
            text = statusTime,
            color = Color.Gray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        )
    }
}

@Composable
fun CircularProgressIndicator(
    progress: Float,
    strokeWidth: Dp,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.Canvas(modifier = modifier) {
        val strokeWidthPx = strokeWidth.toPx()
        val radius = (size.minDimension - strokeWidthPx) / 2
        val center = Offset(size.width / 2, size.height / 2)
        
        // 绘制背景圆环（灰色）
        drawCircularProgressBackground(center, radius, strokeWidthPx)
        
        // 绘制彩色进度条
        drawCircularProgress(center, radius, strokeWidthPx, progress)
    }
}

private fun DrawScope.drawCircularProgressBackground(
    center: Offset,
    radius: Float,
    strokeWidthPx: Float
) {
    drawCircle(
        color = Color.DarkGray,
        radius = radius,
        center = center,
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidthPx)
    )
}

private fun DrawScope.drawCircularProgress(
    center: Offset,
    radius: Float,
    strokeWidthPx: Float,
    progress: Float
) {
    // 计算进度角度
    val angleInDegrees = progress * 360
    
    // 从红色到绿色的颜色渐变
    val color = when {
        progress >= 0.7 -> Color.Green
        progress >= 0.4 -> Color.Yellow
        progress >= 0.2 -> Color(0xFFFFA500) // Orange
        else -> Color.Red
    }
    
    // 绘制彩色进度圆弧
    drawArc(
        color = color,
        startAngle = -90f,
        sweepAngle = angleInDegrees,
        useCenter = false,
        topLeft = Offset(center.x - radius, center.y - radius),
        size = Size(radius * 2, radius * 2),
        style = androidx.compose.ui.graphics.drawscope.Stroke(
            width = strokeWidthPx,
            cap = StrokeCap.Round
        )
    )
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun SanMonitorPreview() {
    SanMonitorApp()
}

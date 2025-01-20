package com.example.myapplication



/*
TO DO:
        -- need battery gauges
        -- temps are still black, problem with library
        -- design UI on ipad

 */

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.github.anastr.speedometer.PointerSpeedometer
import com.github.anastr.speedometer.SpeedView
import com.github.anastr.speedometer.TubeSpeedometer
import kotlin.random.Random
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.github.anastr.speedometer.components.text.SpeedText
import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.persistentListOf
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val driverInfo = mutableListOf("Average Speed: 0 m/s", "Average Temperature: 0°F")
    val mainBatteryInfo = mutableListOf("Voltage: 0 V", "Current: 0 A")
    val supBatteryInfo = mutableListOf("Voltage: 0 V", "Current: 0 A")
    val arr1Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
    val arr2Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
    val arr3Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
    val motorInfo = mutableListOf("Power: 0 W", "Temperature: 0°F", "Current: 0 A")

    val info = mutableMapOf(
        "Driver Info" to driverInfo,
        "Main Battery" to mainBatteryInfo,
        "Supplemental" to supBatteryInfo,
        "Array 1" to arr1Info,
        "Array 2" to arr2Info,
        "Array 3" to arr3Info,
        "Motor" to motorInfo
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(25, 25, 25)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(25, 25, 25))
        ) {
            // TAMU Solar Logo at the top left
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(80, 0, 0))
                    .padding(16.dp)
            ) {
                GreetingImage()
            }

            // Data grid
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(1)
            ) {
                items(info.entries.size) { index ->
                    val entry = info.entries.elementAt(index)
                    PointerSpeedometer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        name = entry.key,
                        data = entry.value
                    )
                }
            }
        }
    }
}

@Composable
fun PointerSpeedometer(name: String, data: List<String>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Handle specific rows
        when (name) {
            "Driver Info" -> {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    var speed by remember { mutableStateOf(0f) }
                    val currentSpeed by animateFloatAsState(
                        targetValue = speed,
                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
                    )

                    var temperature by remember { mutableStateOf(0f) }
                    val currentTemperature by animateFloatAsState(
                        targetValue = temperature,
                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                        Text(text = "Speed", fontSize = 12.sp, color = Color.White)
                        PointerSpeedometer(
                            modifier = Modifier.size(150.dp),
                            speed = currentSpeed,
                            backgroundCircleColor = Color(80, 0, 0),
                            barColor = Color(255, 255, 255),
                            maxSpeed = 75f,
                            unit = "mph"
                        )
                        Button(onClick = { speed = Random.nextFloat() * 100 }) {
                            Text("Random speed")
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                        Text(text = "Temperature", fontSize = 12.sp, color = Color.White)
                        TubeSpeedometer(
                            modifier = Modifier.size(150.dp),
                            speed = currentTemperature,
                            backgroundCircleColor = Color(80, 0, 0),
                            unit = "°F"
                        )
                        Button(onClick = { temperature = Random.nextFloat() * 120 - 10 }) {
                            Text("Update Temperature")
                        }
                    }
                }
            }

            "Main Battery", "Supplemental" -> {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val image = painterResource(id = R.drawable.voltage_image)
                    Image(
                        painter = image,
                        contentDescription = "Voltage Icon",
                        modifier = Modifier.size(25.dp).padding(end = 8.dp)
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                        Text(text = "00.0V", fontSize = 20.sp, color = Color.White)
                        Text(text = "000 mA", fontSize = 20.sp, color = Color.White)
                    }
                }
            }

            "Array 1", "Array 2", "Array 3" -> {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    data.forEach { metric ->
                        Text(
                            text = metric,
                            fontSize = 12.sp,
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            "Motor" -> {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    var temperature by remember { mutableStateOf(0f) }
                    val currentTemperature by animateFloatAsState(
                        targetValue = temperature,
                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
                    )

                    var current by remember { mutableStateOf(0f) }
                    val currentCurrent by animateFloatAsState(
                        targetValue = current,
                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
                    )

                    var power by remember { mutableStateOf(0f) }
                    val currentPower by animateFloatAsState(
                        targetValue = power,
                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                        Text(text = "Temperature", fontSize = 12.sp, color = Color.White)
                        TubeSpeedometer(
                            modifier = Modifier.size(120.dp),
                            speed = currentTemperature,
                            backgroundCircleColor = Color(80, 0, 0),
                            unit = "°F"
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                        Text(text = "Current", fontSize = 12.sp, color = Color.White)
                        TubeSpeedometer(
                            modifier = Modifier.size(120.dp),
                            speed = currentCurrent,
                            backgroundCircleColor = Color(80, 0, 0),
                            unit = "A"
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                        Text(text = "Power", fontSize = 12.sp, color = Color.White)
                        TubeSpeedometer(
                            modifier = Modifier.size(120.dp),
                            speed = currentPower,
                            backgroundCircleColor = Color(80, 0, 0),
                            unit = "W"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingImage(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.official_small)
    Row(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "TAMU Solar Car",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(10.dp),
            color = Color.White,
            fontSize = 25.sp
        )
    }
}

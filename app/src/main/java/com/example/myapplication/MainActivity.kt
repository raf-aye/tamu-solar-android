//package com.example.myapplication
//
//
//
///*
//TO DO:
//        -- change background color to dark
//        -- change sensor displays to lighter colors
//        -- design UI on ipad
//
// */
//
//import android.os.Bundle
//import android.view.View
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.animation.core.FastOutSlowInEasing
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyGridItemScope
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.LinearProgressIndicator
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.myapplication.ui.theme.MyApplicationTheme
//import com.github.anastr.speedometer.PointerSpeedometer
//import com.github.anastr.speedometer.SpeedView
//import com.github.anastr.speedometer.TubeSpeedometer
//import kotlin.random.Random
//import android.widget.SeekBar
//import androidx.core.content.ContextCompat
//import com.github.anastr.speedometer.components.text.SpeedText
//import kotlinx.collections.immutable.immutableListOf
//import kotlinx.collections.immutable.persistentListOf
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.foundation.layout.*
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApplicationTheme {
//                MainScreen()
//            }
//        }
//    }
//}
//
//@Composable
//fun MainScreen() {
//    val driverInfo = mutableListOf("Average Speed: 0 m/s", "Average Temperature: 0°F")
//    val mainBatteryInfo = mutableListOf("Voltage: 0 V", "Current: 0 A")
//    val supBatteryInfo = mutableListOf("Voltage: 0 V", "Current: 0 A")
//    val arr1Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
//    val arr2Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
//    val arr3Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
//    val motorInfo = mutableListOf("Power: 0 W", "Temperature: 0°F", "Current: 0 A")
//
//    val info = mutableMapOf(
//        "Driver Info" to driverInfo,
//        "Main Battery" to mainBatteryInfo,
//        "Supplemental" to supBatteryInfo,
//        "Array 1" to arr1Info,
//        "Array 2" to arr2Info,
//        "Array 3" to arr3Info,
//        "Motor" to motorInfo
//    )
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        containerColor = Color(25, 25, 25)
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(Color(25, 25, 25))
//        ) {
//            // TAMU Solar Logo at the top left
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color(80, 0, 0))
//                    .padding(16.dp)
//            ) {
//                GreetingImage()
//            }
//
//            // Data grid
//            LazyVerticalGrid(
//                modifier = Modifier.fillMaxSize(),
//                columns = GridCells.Fixed(1)
//            ) {
//                items(info.entries.size) { index ->
//                    val entry = info.entries.elementAt(index)
//                    PointerSpeedometer(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        name = entry.key,
//                        data = entry.value
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun PointerSpeedometer(name: String, data: List<String>, modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = name,
//                fontSize = 30.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            )
//        }
//
//        // Handle specific rows
//        when (name) {
//            "Driver Info" -> {
//                Row(
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    var speed by remember { mutableStateOf(0f) }
//                    val currentSpeed by animateFloatAsState(
//                        targetValue = speed,
//                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
//                    )
//
//                    var temperature by remember { mutableStateOf(0f) }
//                    val currentTemperature by animateFloatAsState(
//                        targetValue = temperature,
//                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
//                    )
//
//                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
//                        Text(text = "Speed", fontSize = 12.sp, color = Color.White)
//                        PointerSpeedometer(
//                            modifier = Modifier.size(150.dp),
//                            speed = currentSpeed,
//                            backgroundCircleColor = Color(80, 0, 0),
//                            barColor = Color(255, 255, 255),
//                            maxSpeed = 75f,
//                            unit = "mph"
//                        )
//                        Button(onClick = { speed = Random.nextFloat() * 100 }) {
//                            Text("Random speed")
//                        }
//                    }
//
//                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
//                        Text(text = "Temperature", fontSize = 12.sp, color = Color.White)
//                        TubeSpeedometer(
//                            modifier = Modifier.size(150.dp),
//                            speed = currentTemperature,
//                            backgroundCircleColor = Color(80, 0, 0),
//                            unit = "°F"
//                        )
//                        Button(onClick = { temperature = Random.nextFloat() * 120 - 10 }) {
//                            Text("Update Temperature")
//                        }
//                    }
//                }
//            }
//
//            "Main Battery", "Supplemental" -> {
//                Row(
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    val image = painterResource(id = R.drawable.voltage_image)
//                    Image(
//                        painter = image,
//                        contentDescription = "Voltage Icon",
//                        modifier = Modifier.size(25.dp).padding(end = 8.dp)
//                    )
//
//                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
//                        Text(text = "00.0V", fontSize = 20.sp, color = Color.White)
//                        Text(text = "000 mA", fontSize = 20.sp, color = Color.White)
//                    }
//                }
//            }
//
//            "Array 1", "Array 2", "Array 3" -> {
//                Row(
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    data.forEach { metric ->
//                        Text(
//                            text = metric,
//                            fontSize = 12.sp,
//                            color = Color.White,
//                            modifier = Modifier.weight(1f)
//                        )
//                    }
//                }
//            }
//
//            "Motor" -> {
//                Row(
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    var temperature by remember { mutableStateOf(0f) }
//                    val currentTemperature by animateFloatAsState(
//                        targetValue = temperature,
//                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
//                    )
//
//                    var current by remember { mutableStateOf(0f) }
//                    val currentCurrent by animateFloatAsState(
//                        targetValue = current,
//                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
//                    )
//
//                    var power by remember { mutableStateOf(0f) }
//                    val currentPower by animateFloatAsState(
//                        targetValue = power,
//                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
//                    )
//
//                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
//                        Text(text = "Temperature", fontSize = 12.sp, color = Color.White)
//                        TubeSpeedometer(
//                            modifier = Modifier.size(120.dp),
//                            speed = currentTemperature,
//                            backgroundCircleColor = Color(80, 0, 0),
//                            unit = "°F"
//                        )
//                    }
//
//                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
//                        Text(text = "Current", fontSize = 12.sp, color = Color.White)
//                        TubeSpeedometer(
//                            modifier = Modifier.size(120.dp),
//                            speed = currentCurrent,
//                            backgroundCircleColor = Color(80, 0, 0),
//                            unit = "A"
//                        )
//                    }
//
//                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
//                        Text(text = "Power", fontSize = 12.sp, color = Color.White)
//                        TubeSpeedometer(
//                            modifier = Modifier.size(120.dp),
//                            speed = currentPower,
//                            backgroundCircleColor = Color(80, 0, 0),
//                            unit = "W"
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun GreetingImage(modifier: Modifier = Modifier) {
//    val image = painterResource(R.drawable.official_small)
//    Row(modifier) {
//        Image(
//            painter = image,
//            contentDescription = null,
//            modifier = Modifier.size(100.dp)
//        )
//        Text(
//            text = "TAMU Solar Car",
//            modifier = Modifier
//                .align(Alignment.CenterVertically)
//                .padding(10.dp),
//            color = Color.White,
//            fontSize = 25.sp
//        )
//    }
//}

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.anastr.speedometer.SpeedView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlinx.coroutines.delay

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// Custom colors for the solar dashboard
private val DarkMaroon = Color(0xFF800000)
private val DarkBackground = Color(0xFF121212)
private val DarkSurface = Color(0xFF1E1E1E)
private val AccentColor = Color(0xFF500000)

// Create a custom dark color scheme
private val DarkColorScheme = darkColorScheme(
    primary = DarkMaroon,
    secondary = AccentColor,
    tertiary = Color.White,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = DarkBackground,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun SolarDashboardTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolarDashboardTheme {
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    var currentTime by remember { mutableStateOf(System.currentTimeMillis()) }

    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            currentTime = System.currentTimeMillis()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        TopBar(currentTime)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { PrimaryMetrics() }
            item { BatterySection() }
            item { SolarArraySection() }
            item { MotorSection() }
            item { EnvironmentalSection() }
            item { AlertSection() }
        }
    }
}

@Composable
fun TopBar(currentTime: Long) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF800000))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.official_small),
                contentDescription = "TAMU Logo",
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = "TAMU Solar Racing",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Text(
            text = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(currentTime)),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Composable
fun PrimaryMetrics() {
    var speed by remember { mutableStateOf(0f) }
    var distance by remember { mutableStateOf(0f) }
    var elevation by remember { mutableStateOf(0f) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Primary Metrics",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SpeedometerDisplay(
                    value = speed,
                    maxValue = 100f,
                    title = "Speed",
                    unit = "mph"
                )
                MetricDisplay(
                    value = distance,
                    title = "Distance",
                    unit = "mi"
                )
                MetricDisplay(
                    value = elevation,
                    title = "Elevation",
                    unit = "ft"
                )
            }
        }
    }
}

@Composable
fun BatterySection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Battery Status",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BatteryDisplay(
                    title = "Main Battery",
                    voltage = 48.5f,
                    current = 15.2f,
                    charge = 85f
                )
                BatteryDisplay(
                    title = "Auxiliary Battery",
                    voltage = 12.1f,
                    current = 2.5f,
                    charge = 92f
                )
            }
        }
    }
}

@Composable
fun SolarArraySection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Solar Arrays",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(3) { index ->
                    ArrayDisplay(
                        title = "Array ${index + 1}",
                        voltage = 24.5f,
                        current = 8.2f,
                        power = 200.3f
                    )
                }
            }
        }
    }
}

@Composable
fun MotorSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Motor Status",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                MetricDisplay(
                    value = 2500f,
                    title = "Power",
                    unit = "W"
                )
                MetricDisplay(
                    value = 65f,
                    title = "Temperature",
                    unit = "°F"
                )
                MetricDisplay(
                    value = 45f,
                    title = "Current",
                    unit = "A"
                )
            }
        }
    }
}

@Composable
fun EnvironmentalSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Environmental",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherDisplay(
                    temperature = 75f,
                    windSpeed = 12f,
                    humidity = 45f
                )
            }
        }
    }
}

@Composable
fun AlertSection() {
    var alerts by remember { mutableStateOf(listOf<String>()) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "System Alerts",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            if (alerts.isEmpty()) {
                Text(
                    "No active alerts",
                    color = Color.Green,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            } else {
                alerts.forEach { alert ->
                    Text(
                        text = alert,
                        color = Color.Red,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SpeedometerDisplay(value: Float, maxValue: Float, title: String, unit: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, color = Color.White, fontSize = 16.sp)
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = value / maxValue,
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF800000),
                trackColor = Color(0xFF333333)
            )
            Text(
                "${value.roundToInt()} $unit",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun MetricDisplay(value: Float, title: String, unit: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, color = Color.White, fontSize = 16.sp)
        Text(
            "${value.roundToInt()} $unit",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BatteryDisplay(title: String, voltage: Float, current: Float, charge: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, color = Color.White, fontSize = 16.sp)
        LinearProgressIndicator(
            progress = charge / 100,
            modifier = Modifier
                .width(100.dp)
                .height(8.dp),
            color = if (charge > 20f) Color(0xFF00C853) else Color.Red,
            trackColor = Color(0xFF333333)
        )
        Text("${voltage}V / ${current}A", color = Color.White, fontSize = 14.sp)
        Text("${charge.roundToInt()}%", color = Color.White, fontSize = 18.sp)
    }
}

@Composable
fun ArrayDisplay(title: String, voltage: Float, current: Float, power: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, color = Color.White, fontSize = 16.sp)
        Text("${voltage}V", color = Color.White, fontSize = 14.sp)
        Text("${current}A", color = Color.White, fontSize = 14.sp)
        Text("${power}W", color = Color.White, fontSize = 18.sp)
    }
}

@Composable
fun WeatherDisplay(temperature: Float, windSpeed: Float, humidity: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MetricDisplay(
            value = temperature,
            title = "Temperature",
            unit = "°F"
        )
        MetricDisplay(
            value = windSpeed,
            title = "Wind Speed",
            unit = "mph"
        )
        MetricDisplay(
            value = humidity,
            title = "Humidity",
            unit = "%"
        )
    }
}

package com.example.myapplication


import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        // TAMU Solar Logo positioned at top left

                        GreetingImage(
                            modifier = Modifier
                        )
                        val driverInfo = mutableListOf("Average Speed: 0 m/s", "Average Temperature: 0°F")
                        val mainBatteryInfo = mutableListOf("Voltage: 0 V", "Current: 0 A")
                        val supBatteryInfo = mutableListOf("Voltage: 0 V", "Current: 0 A")
                        val arr1Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
                        val arr2Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
                        val arr3Info = mutableListOf("Voltage: 0 V", "Current: 0 A", "Power: 0 W")
                        val motorInfo = mutableListOf("Power: 0 V", "Temperature: 0°F")
                        val info = mutableMapOf("Driver Info" to driverInfo, "Main Battery" to mainBatteryInfo, "Supplemental" to supBatteryInfo, "Array 1" to arr1Info, "Array 2" to arr2Info, "Array 3" to arr3Info, "Motor" to motorInfo);
                        DataGrid(
                            data = info
                        )

                    }
                    var speed by remember { mutableStateOf(0f) }
                    val currentSpeed by animateFloatAsState(
                        targetValue = speed,
                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        PointerSpeedometer(
                            modifier = Modifier.size(250.dp),
                            speed = currentSpeed,
                        )
                        Button(
                            onClick = {
                                // Change speed to start the animation
                                speed = Random.nextFloat() * 100
                            },
                        ) {
                            Text("Random speed")
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun LazyGridItemScope.DataPoint(name: String, data: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Surface at the top
        Surface(color = Color.Green, modifier = Modifier.padding(10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) { // This padding changes size of box!
                Text(
                    text = name,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Box with "Data Point" below the Surface
        for (value in data) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(
                    text = value,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }
}

@Composable
fun DataGrid(
    modifier : Modifier = Modifier,
    data : Map<String, List<String>>
) {
    // columns = GridCells.Adaptive(dp size of each cell) or GridCells.Fixed(fixed number of cells)
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
    ) {
        items(data.entries.size) { d ->
            val entry = data.entries.elementAt(d)
            DataPoint(
                modifier = Modifier.aspectRatio(1f),
                name = entry.key,
                data = entry.value
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {

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
            text = "TAMU Solar Car Racing",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(10.dp),
            fontSize = 25.sp
        )
    }
}


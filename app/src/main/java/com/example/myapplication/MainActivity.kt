package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

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
                                .padding(25.dp)
                        )

                        val info = listOf("1", "2", "3", "4", "5")
                        DataGrid(
                            data = info
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LazyGridItemScope.DataPoint(name: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Surface at the top
        Surface(color = Color.Green, modifier = Modifier.padding(10.dp)) {
            Box(modifier = Modifier.fillMaxSize().padding(10.dp)) { // This padding changes size of box!
                Text(
                    text = name,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Box with "Data Point" below the Surface
        Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(
                text = "Data Point",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun DataGrid(
    modifier : Modifier = Modifier,
    data : List<String>
) {
    // columns = GridCells.Adaptive(dp size of each cell) or GridCells.Fixed(fixed number of cells)
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(5),
    ) {
        items(data.size) { d ->
            DataPoint(
                modifier = Modifier.aspectRatio(1f),
                name = data[d]
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
            modifier = Modifier.align(Alignment.CenterVertically).padding(10.dp),
            fontSize = 25.sp
        )
    }
}


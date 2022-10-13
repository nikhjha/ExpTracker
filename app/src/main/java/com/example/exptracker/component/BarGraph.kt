package com.example.exptracker.component
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun BarGraph(
    data: List<Float>,
    max_value: Int,
    colors : List<Color>
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            var temp = 0
            // graph
            data.forEach {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .width(20.dp)
                        .fillMaxHeight(it / max_value)
                        .background(colors[temp])
                        .clickable {
                            Toast
                                .makeText(context, it.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                )
                temp += 1
            }
        }

    }

}

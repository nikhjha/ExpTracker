package com.example.exptracker.component

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp


@Composable
fun LineGraph(
    modifier : Modifier,
    xValues: List<Int>,
    yValues: List<Int>,
    points: List<Float>,
    verticalStep: Int,
    paddingSpace: Dp,
) {
    val controlPoints1 = mutableListOf<PointF>()
    val controlPoints2 = mutableListOf<PointF>()
    val coordinates = mutableListOf<PointF>()
    Box(
        modifier = modifier
            .background(Color.White),
        contentAlignment = Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
        ) {
            controlPoints1.clear()
            controlPoints2.clear()
            coordinates.clear()
            val xAxisSpace = (size.width - paddingSpace.toPx()) / (xValues.size - 1)
            val yAxisSpace = size.height / yValues.size
            /** placing our x axis points */
            for (i in points.indices) {
                val x1 = xAxisSpace * xValues[i]
                val y1 = size.height - (yAxisSpace * (points[i]/verticalStep.toFloat()))
                coordinates.add(PointF(x1,y1))
                /** drawing circles to indicate all the points */
                drawCircle(
                    color = Color.Red,
                    radius = 10f,
                    center = Offset(x1,y1)
                )
            }
            /** calculating the connection points */
            for (i in 1 until coordinates.size) {
                controlPoints1.add(PointF((coordinates[i].x + coordinates[i - 1].x) / 2, coordinates[i - 1].y))
                controlPoints2.add(PointF((coordinates[i].x + coordinates[i - 1].x) / 2, coordinates[i].y))
            }
            /** drawing the path */
            val stroke = Path().apply {
                reset()
                moveTo(coordinates.first().x, coordinates.first().y)
                for (i in 0 until coordinates.size - 1) {
                    cubicTo(
                        controlPoints1[i].x,controlPoints1[i].y,
                        controlPoints2[i].x,controlPoints2[i].y,
                        coordinates[i + 1].x,coordinates[i + 1].y
                    )
                }
            }
            /** filling the area under the path */
            val fillPath = android.graphics.Path(stroke.asAndroidPath())
                .asComposePath()
                .apply {
                    lineTo(xAxisSpace * xValues.last(), size.height - yAxisSpace)
                    lineTo(xAxisSpace, size.height - yAxisSpace)
                    close()
                }
            drawPath(
                fillPath,
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Cyan,
                        Color.Transparent,
                    ),
                    endY = size.height - yAxisSpace
                ),
            )
            drawPath(
                stroke,
                color = Color.Black,
                style = Stroke(
                    width = 5f,
                    cap = StrokeCap.Round
                )
            )
        }
    }
}
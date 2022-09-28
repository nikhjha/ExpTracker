package com.example.exptracker.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exptracker.R
import com.example.exptracker.data.CustomMonth




@Composable
fun NotificationBar(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val profileImage = painterResource(id = R.drawable.user)
        val notificationIcon = painterResource(id = R.drawable.notifiaction)
        Image(
            painter = profileImage,
            contentDescription = "profile_pic",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        )
        SelectMonth(CustomMonth.Oct, {})
        var expanded by remember { mutableStateOf(false) }
        Box() {
            IconButton(onClick = {
                expanded = true
            }) {
                Icon(
                    painter = notificationIcon,
                    contentDescription = "notification",
                    tint = MaterialTheme.colors.primary
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .background(color = Color.White)
            ) {
                Text(
                    "No notifications available",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }

    }
}
package com.example.exptracker.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
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


@Composable
fun SelectBox() {
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedButton(
            onClick = { expanded = !expanded },
            border = BorderStroke(1.dp, color = MaterialTheme.colors.primary),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFF6E5)),
            contentPadding = PaddingValues(end = 16.dp, start = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.ArrowDropDown,
                    contentDescription = "DropDown",
                    tint = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("October")
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
            }) {
                Text("Add")
            }
            DropdownMenuItem(onClick = {
            }) {
                Text("Remove")
            }
            DropdownMenuItem(onClick = {
            }) {
                Text("Clear")
            }
        }
    }
}

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
        SelectBox()
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
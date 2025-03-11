package com.natxo.pruebafirebase.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.natxo.pruebafirebase.R
import com.natxo.pruebafirebase.ui.theme.BackgroundButton
import com.natxo.pruebafirebase.ui.theme.Black
import com.natxo.pruebafirebase.ui.theme.Cyan
import com.natxo.pruebafirebase.ui.theme.Gray
import com.natxo.pruebafirebase.ui.theme.ShapeButton


@Composable
fun ProfileScreen(auth: FirebaseAuth, navigateToInitial: () -> Unit, navigateToHome: () -> Unit) {

    val email = auth.currentUser?.email

    Column(
    modifier = Modifier
    .fillMaxSize()
    .background(Brush.verticalGradient(listOf(Gray, Black), startY = 0f, endY = 600f)),
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_24),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(24.dp)
                    .clickable { navigateToHome() }
            )
        }
        Spacer(modifier = Modifier.weight(0.2f))
        Image(
            painter = painterResource(id = R.drawable.warframe_icon),
            contentDescription = "warframe_icon"
        )
        Text(
            "Profile",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(0.2f))
        if (email != null) {
            Text(
                email,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Close sesion",
            color = Color.Red,
            modifier = Modifier
                .padding(24.dp)
                .clickable { navigateToInitial() },
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

package com.natxo.pruebafirebase.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natxo.pruebafirebase.R
import com.natxo.pruebafirebase.presentation.model.Relic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navigateToHome: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val opciones = listOf("Lith", "Neo", "Meso", "Axi")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.Gray, Color.Black), startY = 0f, endY = 600f)),
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
            "Relic maker",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(0.2f))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = tipo,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo") },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .padding(16.dp)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                opciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            tipo = opcion
                            expanded = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                val relic = createRelic(name, tipo)
                Log.i("aaa","${relic}")
                addToFirebase(relic)
                navigateToHome()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Add new relic")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

fun createRelic(name: String, tipo: String): Relic {
    val imageMap = mapOf(
        "Lith" to "https://static.wikia.nocookie.net/warframe/images/d/df/LithRelicIntact.png/revision/latest?cb=20230224234350",
        "Neo" to "https://static.wikia.nocookie.net/warframe/images/9/97/NeoRelicIntact.png/revision/latest?cb=20230224234154",
        "Meso" to "https://static.wikia.nocookie.net/warframe/images/e/e0/MesoRelicIntact.png/revision/latest?cb=20230224234310",
        "Axi" to "https://static.wikia.nocookie.net/warframe/images/b/bd/AxiRelicIntact.png/revision/latest?cb=20230224233954"
    )

    val imageUrl = imageMap[tipo] ?: ""
    val relicname = "$tipo ${name.replaceFirstChar { it.uppercase() }}"
    return Relic(name = relicname, image = imageUrl)
}

fun addToFirebase(relic: Relic) {
    val db: FirebaseFirestore = Firebase.firestore
    relic.name?.let {
        db.collection("relic")
            .document(it)
            .set(relic)
            .addOnSuccessListener {
                Log.i("aaa","success")
            }
            .addOnFailureListener { e ->
                Log.i("aaa","${e.message}")
            }
    }
}

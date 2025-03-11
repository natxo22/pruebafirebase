package com.natxo.pruebafirebase.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natxo.pruebafirebase.presentation.model.Relic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewmodel : ViewModel() {

    private var db: FirebaseFirestore = Firebase.firestore

    private val _relics = MutableStateFlow<List<Relic>>(emptyList())
    val relic: StateFlow<List<Relic>> = _relics

    init {

        //loadData()

        getRelics()

    }

    private fun loadData() {
        val lith = "https://static.wikia.nocookie.net/warframe/images/d/df/LithRelicIntact.png/revision/latest?cb=20230224234350"
        val neo = "https://static.wikia.nocookie.net/warframe/images/9/97/NeoRelicIntact.png/revision/latest?cb=20230224234154"
        val meso= "https://static.wikia.nocookie.net/warframe/images/e/e0/MesoRelicIntact.png/revision/latest?cb=20230224234310"
        val axi = "https://static.wikia.nocookie.net/warframe/images/b/bd/AxiRelicIntact.png/revision/latest?cb=20230224233954"
        val relics = listOf(
            // Lith Relics
            Relic(name = "Lith A6", image = lith),
            Relic(name = "Lith C7", image = lith),
            Relic(name = "Lith G13", image = lith),
            Relic(name = "Lith M9", image = lith),
            Relic(name = "Lith N16", image = lith),
            Relic(name = "Lith P9", image = lith),
            Relic(name = "Lith W4", image = lith),
            Relic(name = "Lith X1", image = lith),

            // Meso Relics
            Relic(name = "Meso A7", image = meso),
            Relic(name = "Meso E6", image = meso),
            Relic(name = "Meso F5", image = meso),
            Relic(name = "Meso G8", image = meso),
            Relic(name = "Meso N11", image = meso),
            Relic(name = "Meso N17", image = meso),
            Relic(name = "Meso T7", image = meso),
            Relic(name = "Meso V10", image = meso),

            // Neo Relics
            Relic(name = "Neo A13", image = neo),
            Relic(name = "Neo G8", image = neo),
            Relic(name = "Neo L4", image = neo),
            Relic(name = "Neo P7", image = neo),
            Relic(name = "Neo Q1", image = neo),
            Relic(name = "Neo V9", image = neo),
            Relic(name = "Neo W2", image = neo),
            Relic(name = "Neo Z11", image = neo),

            // Axi Relics
            Relic(name = "Axi A19", image = axi),
            Relic(name = "Axi G14", image = axi),
            Relic(name = "Axi H8", image = axi),
            Relic(name = "Axi O6", image = axi),
            Relic(name = "Axi S16", image = axi),
            Relic(name = "Axi S17", image = axi),
            Relic(name = "Axi S8", image = axi),
            Relic(name = "Axi T12", image = axi),
            Relic(name = "Axi V10", image = axi)
        ).forEach { relic ->
            relic.name?.let {
                db.collection("relic")
                    .document(it) // Usamos el nombre como ID del documento
                    .set(relic)
                    .addOnSuccessListener {
                        Log.i("aaa","success")
                    }
                    .addOnFailureListener { e ->
                        Log.i("aaa","${e.message}")
                    }
            }
        }

    }

    private fun getRelics() {
        viewModelScope.launch {
            val result: List<Relic> = withContext(Dispatchers.IO) {
                getAllRelics()
            }
            _relics.value = result
        }
    }

    private suspend fun getAllRelics(): List<Relic> {
        return try {
            db.collection("relic").get().await().documents.mapNotNull { snapshot ->
                snapshot.toObject(Relic::class.java)
            }
        } catch (e: Exception) {
            Log.e("aaa", "Error obteniendo relics: ${e.message}", e)
            emptyList()
        }
    }

}
package com.natxo.pruebafirebase.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.natxo.pruebafirebase.R
import com.natxo.pruebafirebase.presentation.model.Relic
import com.natxo.pruebafirebase.ui.theme.Black

@Composable
fun HomeScreen(
    viewmodel: HomeViewmodel = HomeViewmodel(),
    navigateToRelic:(Relic) -> Unit,
    navigateToProfile:() -> Unit,
    navigateToAdd:() -> Unit)
{

    val relics: State<List<Relic>> = viewmodel.relic.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Black)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 24.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_group_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navigateToProfile()
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.baseline_edit_24),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navigateToAdd()
                    }
            )
        }

        Text(
            "In vault relics",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(relics.value) { r ->
                RelicItem(r, { navigateToRelic(r) })
            }
        }
    }
}

@Composable
fun RelicItem(relic: Relic, navigateToRelic:() -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                navigateToRelic()
            }
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            model = relic.image,
            contentDescription = "Artists image",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            relic.name.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
    }
}
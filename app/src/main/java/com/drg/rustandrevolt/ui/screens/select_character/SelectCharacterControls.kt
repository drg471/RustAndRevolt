package com.drg.rustandrevolt.ui.screens.select_character

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drg.rustandrevolt.R

@Composable
fun SelectCharacterControls() {
    val context = LocalContext.current
    val characterName : String = context.getString(R.string.characterName)

    //Columna 2 (SlidePictureBox + label nombre personaje)
    Column (modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.Green)
        .fillMaxHeight(0.85f)
    ){
        Text(text = "Columna 2")

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f)
                .border(1.dp, Color.Black)
                .align(Alignment.CenterHorizontally),
            Arrangement.SpaceBetween
        ) {
            //Boton Atras
            Button(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 15.dp)
                , onClick = { }
            ) {
                Text("<-")
            }

            //Imagen Personaje
            Image(
                painter = painterResource(R.drawable.imagedflt),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 230.dp, height = 400.dp)
                    .border(1.dp, Color.Black)
                    .align(Alignment.CenterVertically)
            )

            //Boton Adelante
            Button(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 15.dp)
                , onClick = { }
            ) {
                Text("->")
            }
        }

        Text(
            characterName,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}
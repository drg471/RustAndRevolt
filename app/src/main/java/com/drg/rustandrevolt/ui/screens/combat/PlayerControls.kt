package com.drg.rustandrevolt.ui.screens.combat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.drg.rustandrevolt.ui.navigation.AppScreens

@Composable
fun PlayerControls(navigateToHomeScreen : () -> Unit) {
    var vidaJugador : Float = 0.8f

    Column (modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.Magenta)
        .fillMaxHeight(1f)
    ){
        Text(text = "Pantalla controles jugador", fontSize = 15.sp)

        //Vida Jugador
        LinearProgressIndicator(
            progress = 0.8f,
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            color = if (vidaJugador < 0.3f) Color.Red else if (vidaJugador < 0.6f && vidaJugador > 0.3f) Color.Yellow else Color.Green,
        )

        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

        //Maná Jugador
        LinearProgressIndicator(
            progress = 0.4f,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color.Blue,
        )

        Spacer(modifier = Modifier.fillMaxWidth().height(25.dp))

        //Fila de botones
        Row(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            ,horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //Botón de Vida
            Button(modifier = Modifier
                .background(Color.White)
                .height(120.dp)
                .width(70.dp),
                onClick = { navigateToHomeScreen() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Vida")
            }

            //Botón de Ataque 1
            Button(modifier = Modifier
                .background(Color.White)
                .height(120.dp)
                .width(70.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Atq1")
            }

            //Botón de Ataque 2
            Button(modifier = Modifier
                .background(Color.White)
                .height(120.dp)
                .width(70.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Atq2")
            }

            //Botón de Ataque 3
            Button(modifier = Modifier
                .background(Color.White)
                .height(120.dp)
                .width(70.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Atq3")
            }

            //Botón de Ataque Especial
            Button(modifier = Modifier
                .background(Color.White)
                .height(120.dp)
                .width(70.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp),
                content = { Text("SAtq") },
                enabled = false
            )
        }
    }
}
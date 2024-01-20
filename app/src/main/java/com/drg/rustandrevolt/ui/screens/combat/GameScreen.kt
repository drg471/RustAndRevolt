package com.drg.rustandrevolt.ui.screens.combat

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
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
fun GameScreen() {
    val context = LocalContext.current
    val enemyName : String = context.getString(R.string.enemyName)

    //Pantalla de juego

    var vidaEnemigo : Float = 0.2f

    //Columna -> Nombre Enemigo + Vida Enemigo + box imagenes personajes
    Column(modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.Red)
        .fillMaxHeight(0.7f)
    ) {
        Text(text = "Pantalla juego", fontSize = 15.sp)

        //Texto Nombre Enemigo
        Text(text = enemyName, fontSize = 18.sp)

        //Vida Enemigo
        LinearProgressIndicator(
            progress = vidaEnemigo,
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            color = if (vidaEnemigo < 0.3f) Color.Red else if (vidaEnemigo < 0.6f && vidaEnemigo > 0.3f) Color.Yellow else Color.Green
        )

        //Contenedor de Imagenes de Personajes en la batalla
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            //Fila Imagen enemigo + daño
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(1.dp, Color.Black),
                horizontalArrangement = Arrangement.End

            ){
                Text(
                    text = "-15",
                    fontSize = 50.sp,
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .align(Alignment.CenterVertically)
                )

                Image(
                    painter = painterResource(R.drawable.imagedflt),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 150.dp, height = 300.dp)
                        .border(1.dp, Color.Black)
                )
            }

            //Fila Imagen jugador + daño
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .border(1.dp, Color.Black).align(Alignment.BottomStart)
            ) {
                Image(
                    painter = painterResource(R.drawable.imageusrdflt),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 230.dp, height = 175.dp)
                        .border(1.dp, Color.Black)
                        .align(Alignment.Bottom)
                )
                Text(
                    text = "-5",
                    fontSize = 50.sp,
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
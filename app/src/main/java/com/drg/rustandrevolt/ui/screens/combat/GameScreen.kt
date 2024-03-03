package com.drg.rustandrevolt.ui.screens.combat

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.screens.home.TYPEFACE
import com.drg.rustandrevolt.viewmodels.CombatViewModel
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun GameScreen(viewModel: CombatViewModel = hiltViewModel()) {
    val context = LocalContext.current
    viewModel.context = context

    var enemyAIName = viewModel.mutableEnemyAIName
    var enemyAILife = viewModel.mutableEnemyAILife
    var enemyAIDamage = viewModel.mutableEnemyAIDamage
    var playerDamage = viewModel.mutablePlayerDamage
    var playerDamageRedColor = viewModel.mutablePlayerDamageRedColor
    var enemyAIDamageRedColor = viewModel.mutableEnemyAIDamageRedColor
    var playerImage: Int = viewModel.imagePlayerCombat
    var enemyImage: Int = viewModel.imageEnemyCombat
    var background: Int = viewModel.mutableBackground

    //Pantalla de juego

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .border(3.dp, Color.Black)
            .paint(
                painterResource(id = background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        //Columna -> Nombre Enemigo + Vida Enemigo + box imagenes personajes
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            //Texto Nombre Enemigo
            Text(
                text = enemyAIName, color = Color.White, style = TextStyle(
                    fontFamily = FontFamily(TYPEFACE),
                    fontSize = 20.sp,
                    letterSpacing = 2.sp,
                    shadow = Shadow(
                        color = Color.Black, // Color del borde
                        blurRadius = 10f, // Radio del desenfoque
                        offset = Offset(1f, 1f) // Desplazamiento del borde
                    )
                ), modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp) // Añadimos margen horizontal de 16dp
            ) {
                LinearProgressIndicator(
                    progress = enemyAILife, // (1.0f) Invertir la dirección de llenado
                    modifier = Modifier
                        .weight(1f) // Ocupa el espacio restante
                        .height(15.dp), color = when {
                        enemyAILife <= 0.25f -> Color.Red
                        enemyAILife <= 0.5f -> Color.Yellow
                        else -> Color.Green
                    }
                )
                Log.i("enemyAILife", enemyAILife.toString())
            }

            //Contenedor de Imagenes de Personajes en la batalla
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                //Fila Imagen enemigo + daño
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(0.dp,0.dp,20.dp,0.dp),
//                        .border(1.dp, Color.Black)
                    horizontalArrangement = Arrangement.End

                ) {
                    Text(
                        text = enemyAIDamage, color = if (enemyAIDamageRedColor) Color.Red else Color.Green, style = TextStyle(
                            fontSize = 65.sp,
                            letterSpacing = 2.sp,
                            shadow = Shadow(
                                color = Color.White, // Color del borde
                                blurRadius = 10f, // Radio del desenfoque
                                offset = Offset(1f, 1f) // Desplazamiento del borde
                            )
                        ),
                        modifier = Modifier
//                            .border(1.dp, Color.Black)
                            .align(Alignment.CenterVertically),
                    )


                    Image(
                        painter = painterResource(enemyImage),
                        contentDescription = null,
                        modifier = Modifier.size(width = 150.dp, height = 300.dp)
//                            .border(1.dp, Color.Black)
                    )
                }

                //Fila Imagen jugador + daño
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
//                        .border(1.dp, Color.Black)
                        .align(Alignment.BottomStart)
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 230.dp, height = 175.dp)
//                            .border(1.dp, Color.Black)
                            .align(Alignment.Bottom)
                    ) {
                        Image(
                            painter = painterResource(playerImage),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = playerDamage, color = if (playerDamageRedColor) Color.Red else Color.Green, style = TextStyle(
                            fontSize = 55.sp,
                            letterSpacing = 2.sp,
                            shadow = Shadow(
                                color = Color.White, // Color del borde
                                blurRadius = 10f, // Radio del desenfoque
                                offset = Offset(1f, 1f) // Desplazamiento del borde
                            )
                        ),
                        //fontWeight = FontWeight.Bold,
                        modifier = Modifier
//                            .border(1.dp, Color.Black)
                            .align(Alignment.CenterVertically),
                    )
                }
            }
        }
    }
}

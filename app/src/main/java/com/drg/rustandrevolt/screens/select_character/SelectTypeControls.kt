package com.drg.rustandrevolt.screens.select_character

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SelectTypeControls {
    companion object{
        @Preview
        @Composable
        fun create() {
            //Columna 1 (label + botones tipos personaje)
            Column (modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Blue)
                .fillMaxHeight(0.25f)
            ){

                //Titulo - Seleccion de Personaje
                Text(text = "Columna 1")
                Text(
                    text = "Selección Personaje",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                //Contenedor de botones de tipos de personaje
                Box(modifier = Modifier
                    .background(Color.Yellow)
                    .align(Alignment.CenterHorizontally)
                    .size(width = 250.dp, height = 150.dp)
                    .border(1.dp, Color.Blue)
                ){
                    //Columna de botones de seleccion del tipo
                    Column {
                        Text(modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                            text = "Botones tipo personaje"
                        )

                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(15.dp))

                        Row (modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            //Boton Tipo Personaje Rebelde
                            Button(modifier = Modifier
                                .height(40.dp)
                                .width(80.dp)
                                , onClick = { }
                            ) {
                                Text("PRe")
                            }

                            //Boton Tipo Personaje Ingeniero
                            Button(modifier = Modifier
                                .height(40.dp)
                                .width(80.dp)
                                , onClick = { }
                            ) {
                                Text("PIn")
                            }
                        }

                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(15.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            //Boton Tipo Personaje Maquina
                            Button(modifier = Modifier
                                .height(40.dp)
                                .width(80.dp)
                                .align(Alignment.Bottom)
                                , onClick = { }
                            ) {
                                Text("PMa")
                            }
                        }
                    }
                }
            }
        }
    }
}
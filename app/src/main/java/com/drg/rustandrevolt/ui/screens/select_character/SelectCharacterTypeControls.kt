package com.drg.rustandrevolt.ui.screens.select_character

import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.screens.home.BACKGROUND_COLOR
import com.drg.rustandrevolt.ui.screens.home.BUTTON_COLOR
import com.drg.rustandrevolt.ui.screens.home.TYPEFACE

@Composable
fun SelectCharacterTypeControls(
    buttonSelectCharacterTypeSound: () -> Unit,
    loadRebelListRoom: () -> Unit,
    loadEngineerList: () -> Unit,
    loadMachineList: () -> Unit,
) {
    val context = LocalContext.current
    val selectCharacter : String = context.getString(R.string.selectCharacter)
    val characterTypeRebel : String = context.getString(R.string.characterTypeRebel)
    val characterTypeEngineer : String = context.getString(R.string.characterTypeEngineer)
    val characterTypeMachine : String = context.getString(R.string.characterTypeMachine)


    //Columna 1 (label + botones tipos personaje)
    Column (modifier = Modifier
        .fillMaxWidth()
//        .border(1.dp, Color.Blue)
        .fillMaxHeight(0.25f)
    ){

        //Titulo - Seleccion de Personaje
//        Text(text = "Columna 1")

        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

        Text(
            text = selectCharacter,
            color = Color.White,
            style = TextStyle(
                fontFamily = FontFamily(TYPEFACE),
                fontSize = 30.sp,
                letterSpacing = 2.sp,
                shadow = Shadow(
                    color = Color.Black, // Color del borde
                    blurRadius = 4f, // Radio del desenfoque
                    offset = Offset(2f, 2f) // Desplazamiento del borde
                )
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        //Contenedor de botones de tipos de personaje
        Box(modifier = Modifier
            .background(Color(BACKGROUND_COLOR))
            .align(Alignment.CenterHorizontally)
            .size(width = 250.dp, height = 150.dp)
//            .border(1.dp, Color.Blue)
        ){
            //Columna de botones de seleccion del tipo
            Column {

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
                        .width(112.dp),
                        colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR)),
                        onClick = {
                            buttonSelectCharacterTypeSound()
                            loadRebelListRoom()
                        }
                    ) {
                        Text(
                            text = characterTypeRebel,
                            style = TextStyle(
                                fontFamily = FontFamily(TYPEFACE),
                                fontSize = 15.sp,
                                letterSpacing = 2.sp
                            )
                        )
                    }

                    //Boton Tipo Personaje Ingeniero
                    Button(modifier = Modifier
                        .height(40.dp)
                        .width(114.dp),
                        colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR)),
                        onClick = {
                            buttonSelectCharacterTypeSound()
                            loadEngineerList()
                        }
                    ) {
                        Text(
                            text = characterTypeEngineer,
                            style = TextStyle(
                                fontFamily = FontFamily(TYPEFACE),
                                fontSize = 15.sp,
                                letterSpacing = 2.sp
                            )
                        )
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
                        .width(112.dp)
                        .align(Alignment.Bottom),
                        colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR)),
                        onClick = {
                            buttonSelectCharacterTypeSound()
                            loadMachineList()
                        }
                    ) {
                        Text(
                            text = characterTypeMachine,
                            style = TextStyle(
                                fontFamily = FontFamily(TYPEFACE),
                                fontSize = 15.sp,
                                letterSpacing = 2.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
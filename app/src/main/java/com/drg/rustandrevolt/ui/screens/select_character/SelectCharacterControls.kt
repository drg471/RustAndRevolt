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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.drg.rustandrevolt.ui.screens.home.BUTTON_COLOR
import com.drg.rustandrevolt.ui.screens.home.TYPEFACE

@Composable
fun SelectCharacterControls(viewModel : CharacterSelectionViewModel = hiltViewModel()) {
    val context = LocalContext.current
    viewModel.context = context

    //Obtiene la lista de personajes y el indice actual del viewmodel
    val characterList = viewModel.characterList
    var currentCharacterIndex = viewModel.currentCharacterIndex
    var currentCharacter = characterList.getOrNull(currentCharacterIndex)

    var characterImageResource : Int = viewModel.currentImageIndex

    //Columna 2 (SlidePictureBox + label nombre personaje)
    Column (modifier = Modifier
        .fillMaxWidth()
//        .border(1.dp, Color.Green)
        .fillMaxHeight(0.85f)
    ){
//        Text(text = "Columna 2")

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f)
//                .border(1.dp, Color.Black)
                .align(Alignment.CenterHorizontally),
            Arrangement.SpaceBetween
        ) {
            //Boton Atras
            Button(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp),
                colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR)),
                onClick = {
                    viewModel.buttonChangeCharacterSound()
                    viewModel.showPreviousCharacter()
                }
            ) {
                Text(text = "<", fontSize = 24.sp)
            }

            //Imagen Personaje
            Image(
                painter = painterResource(characterImageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 230.dp, height = 400.dp)
                    .border(5.dp, Color.Black)
                    .align(Alignment.CenterVertically)
            )

            //Boton Adelante
            Button(modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 10.dp),
                colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR)),
                onClick = {
                    viewModel.buttonChangeCharacterSound()
                    viewModel.showNextCharacter()
                }
            ) {
                Text(">", fontSize = 24.sp)
            }
        }

        //***********************************
        //Mostrar el nombre del personaje actual
        currentCharacter?.let { character ->
            Text(
                text = character.name,
                color = Color.White,
                style = TextStyle(
                    fontFamily = FontFamily(TYPEFACE),
                    fontSize = 20.sp,
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
        }
        //***********************************

    }
}


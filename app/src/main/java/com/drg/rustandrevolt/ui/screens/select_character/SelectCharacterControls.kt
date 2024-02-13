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
import com.drg.rustandrevolt.viewmodels.CharacterSelectionViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.service.AppContextSingleton

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
                , onClick = {
                    viewModel.buttonChangeCharacterSound()
                    viewModel.showPreviousCharacter()
                }
            ) {
                Text("<-")
            }

            //Imagen Personaje
            Image(
                painter = painterResource(characterImageResource),
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
                , onClick = {
                    viewModel.buttonChangeCharacterSound()
                    viewModel.showNextCharacter()
                }
            ) {
                Text("->")
            }
        }

        //***********************************
        //Mostrar el nombre del personaje actual
        currentCharacter?.let { character ->
            Text(
                character.name,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
        //***********************************

    }
}


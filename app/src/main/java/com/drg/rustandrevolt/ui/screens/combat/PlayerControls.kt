package com.drg.rustandrevolt.ui.screens.combat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.entities.normalAttack
import com.drg.rustandrevolt.entities.specialAttack
import com.drg.rustandrevolt.entities.strongAttack
import com.drg.rustandrevolt.entities.veryStrongAttack
import com.drg.rustandrevolt.viewmodels.CombatViewModel

@Composable
fun PlayerControls(navigateToHomeScreen : () -> Unit, viewModel : CombatViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val buttonEndGame : String = context.getString(R.string.button_end_game)

    var playerLife = viewModel.mutablePlayerLife
    var playerChargeSpecialAttack = viewModel.mutablePlayerChargeSpecialAttack
    var PlayerRemainingHealPotions = viewModel.mutablePlayerRemainingHealPotions
    var showSeqText = viewModel.mutableShowSeqText
    var enableBtnHeal = viewModel.mutableEnableBtnHeal
    var enableBtnStrongAttack = viewModel.mutableEnableBtnStrongAttack
    var enableBtnVeryStrongAttack = viewModel.mutableEnableBtnVeryStrongAttack
    var enableBtnSpecialAttack = viewModel.mutableEnableBtnSpecialAttack
    var enableBtnEndGame = viewModel.mutableShowBtnEndGame

    Column (modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.Magenta)
        .fillMaxHeight(1f)
    ){
        Text(text = viewModel.characterPlayer.name, fontSize = 15.sp)

        //Vida Jugador
        LinearProgressIndicator(
            progress = playerLife,
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            color = if (playerLife < 0.25f) Color.Red else if (playerLife < 0.5f && playerLife > 0.3f) Color.Yellow else Color.Green,
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        //Maná Jugador
        LinearProgressIndicator(
            progress = playerChargeSpecialAttack,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color.Blue,
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(25.dp))

        if (showSeqText){
            //Texto mensajes secuencias
            Row(modifier = Modifier
                .fillMaxSize()
                .border(1.dp, Color.Magenta)
                , verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = viewModel.mutableSeqtext, fontSize = 20.sp)
            }
        }
        else if (enableBtnEndGame){
            //Boton partida finalizada
            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .height(40.dp)
                .width(200.dp), onClick = {
                navigateToHomeScreen()
            }
            ) {
                Text(buttonEndGame)
            }

        }
        else{
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
                    onClick = { viewModel.playerHealSequence() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(0.dp),
                    enabled = enableBtnHeal
                ) {
                    Text("Vida (x${PlayerRemainingHealPotions})")
                }

                //Botón de Ataque 1
                Button(modifier = Modifier
                    .background(Color.White)
                    .height(120.dp)
                    .width(70.dp),
                    onClick = { viewModel.playerAttackSequence(normalAttack) },
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
                    onClick = { viewModel.playerAttackSequence(strongAttack) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(0.dp),
                    enabled = enableBtnStrongAttack
                ) {
                    Text("Atq2")
                }

                //Botón de Ataque 3
                Button(modifier = Modifier
                    .background(Color.White)
                    .height(120.dp)
                    .width(70.dp),
                    onClick = { viewModel.playerAttackSequence(veryStrongAttack) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(0.dp),
                    enabled = enableBtnVeryStrongAttack
                ) {
                    Text("Atq3")
                }

                //Botón de Ataque Especial
                Button(modifier = Modifier
                    .background(Color.White)
                    .height(120.dp)
                    .width(70.dp),
                    onClick = { viewModel.playerAttackSequence(specialAttack) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(0.dp),
                    content = { Text("SAtq") },
                    enabled = enableBtnSpecialAttack
                )
            }
        }
    }
}
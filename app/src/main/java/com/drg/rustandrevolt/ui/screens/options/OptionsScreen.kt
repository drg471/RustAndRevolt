package com.drg.rustandrevolt.ui.screens.options

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.MyApplication.Companion.musicPreferences
import com.drg.rustandrevolt.ui.screens.home.BACKGROUND_COLOR
import com.drg.rustandrevolt.ui.screens.home.BUTTON_COLOR
import com.drg.rustandrevolt.ui.screens.home.TYPEFACE
import com.drg.rustandrevolt.ui.theme.RustAndRevoltTheme

@Composable
fun OptionsScreen(
    navigateToHomeScreen : () -> Unit,
    navigateToInstructionsScreen : () -> Unit,
    navigateToLegalScreen : () -> Unit,
    viewModel: OptionsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val optionsTittle : String = context.getString(R.string.options_tittle)
    val buttonSoundOnOff : String = context.getString(R.string.button_sound_onoff)
    val buttonInstructions : String = context.getString(R.string.button_instructions)
    val buttonReturn : String = context.getString(R.string.button_return)
    val buttonLegal : String = context.getString(R.string.button_legal)

    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color(color = BACKGROUND_COLOR)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        var isVibration = viewModel.isVibration

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(color = BACKGROUND_COLOR)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = optionsTittle,
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

            Spacer(modifier = Modifier.height(50.dp))

            //Switch Quitar Sonido App
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(
                    text = buttonSoundOnOff,
                    style = TextStyle(
                        fontFamily = FontFamily(TYPEFACE),
                        fontSize = 18.sp,
                        letterSpacing = 2.sp,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(10.dp).width(10.dp))

                CustomColoredSwitch(
                    checked = musicPreferences.isMusicEnabled,
                    onCheckedChange = { isChecked ->
                        viewModel.buttonSound()
                        musicPreferences.setMusicEnabledPrefs(isChecked, true)
                    },
                    modifier = Modifier.padding(top = 10.dp),
                )
            }

            //Switch Quitar Vibracion App
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Vibración Off/On",
                    style = TextStyle(
                        fontFamily = FontFamily(TYPEFACE),
                        fontSize = 18.sp,
                        letterSpacing = 2.sp,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(10.dp).width(10.dp))

                CustomColoredSwitch(
                    checked = isVibration,
                    onCheckedChange = { isChecked ->
                        viewModel.activateVibration(isChecked)
                        //musicPreferences.setMusicEnabledPrefs(isChecked, true)
                    },
                    modifier = Modifier.padding(top = 10.dp),
                )
            }


            //Boton Instrucciones
            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .height(40.dp)
                .width(200.dp),
                colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR))
                , onClick = {
                    viewModel.buttonSound()
                    navigateToInstructionsScreen()
                }
            ) {
                Text(
                    text = buttonInstructions,
                    style = TextStyle(
                        fontFamily = FontFamily(TYPEFACE),
                        fontSize = 18.sp,
                        letterSpacing = 2.sp
                    )
                )
            }

            //Boton Legal
            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .height(40.dp)
                .width(200.dp),
                colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR))
                , onClick = {
                    viewModel.buttonSound()
                    navigateToLegalScreen()
                }
            ) {
                Text(
                    text = buttonLegal,
                    style = TextStyle(
                        fontFamily = FontFamily(TYPEFACE),
                        fontSize = 18.sp,
                        letterSpacing = 2.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            //Boton volver a pantalla Home
            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .height(40.dp)
                .width(200.dp),
                colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR))
                , onClick = {
                    viewModel.buttonSound()
                    navigateToHomeScreen()
                } //Vuelve a la ultima página guardada en pila
            ) {
                Text(
                    text = buttonReturn,
                    style = TextStyle(
                        fontFamily = FontFamily(TYPEFACE),
                        fontSize = 18.sp,
                        letterSpacing = 2.sp
                    )
                )
            }
        }
    }



}

@Composable
fun CustomColoredSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val thumbColorChecked = Color(BUTTON_COLOR)
    val thumbColorUnchecked = Color.Gray

    // Utiliza el modificador colors para aplicar los colores personalizados al interruptor
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = thumbColorChecked,
            uncheckedThumbColor = thumbColorUnchecked
        ),
        modifier = modifier
    )
}

@Composable
@Preview
fun OptionsScreenPreview() {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    RustAndRevoltTheme(darkTheme = false){
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = LocalContentColor.current
        ) {
            OptionsScreen(
                navigateToHomeScreen = {},
                navigateToInstructionsScreen = {},
                navigateToLegalScreen = {}
            )
        }
    }
}
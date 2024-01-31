package com.drg.rustandrevolt.ui.screens.instructions

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.screens.home.HomeScreen
import com.drg.rustandrevolt.ui.theme.RustAndRevoltTheme
import com.drg.rustandrevolt.usecases.PlayerUseCase
import com.drg.rustandrevolt.viewmodels.PlayerViewModel

@Composable
fun InstructionsScreen(navigateToHomeScreen : () -> Unit) {
    val context = LocalContext.current

    val buttonReturn : String = context.getString(R.string.button_return)

    Column (modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        //Instrucciones
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            BasicTextField(
                value = TextFieldValue("Instrucciones"),
                onValueChange = {},
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        //Boton volver a pantalla Home
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = { navigateToHomeScreen() } //Vuelve a la ultima página guardada en pila
        ) {
            Text(buttonReturn)
        }
    }
}

@Composable
@Preview
fun InstructionsScreenPreview() {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    RustAndRevoltTheme(darkTheme = false){
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = LocalContentColor.current
        ) {
            InstructionsScreen(
                navigateToHomeScreen = {},
            )
        }
    }
}
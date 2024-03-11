package com.drg.rustandrevolt.ui.screens.instructions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.screens.home.BACKGROUND_COLOR
import com.drg.rustandrevolt.ui.screens.home.BUTTON_COLOR
import com.drg.rustandrevolt.ui.screens.home.SEC_BACKGROUND_COLOR
import com.drg.rustandrevolt.ui.screens.home.TYPEFACE
import com.drg.rustandrevolt.ui.theme.RustAndRevoltTheme

@Composable
fun InstructionsScreen(navigateToOptionsScreen: () -> Unit) {
    val context = LocalContext.current

    val buttonReturn: String = context.getString(R.string.button_return)
    val tittleInstructions: String = context.getString(R.string.tittleInstructions)
    val gameInstructions1: String = context.getString(R.string.game_instructions1)
    val gameInstructions2: String = context.getString(R.string.game_instructions2)
    val gameInstructions3: String = context.getString(R.string.game_instructions3)
    val gameInstructions4: String = context.getString(R.string.game_instructions4)
    val gameInstructions5: String = context.getString(R.string.game_instructions5)
    val gameInstructions6: String = context.getString(R.string.game_instructions6)

    //Instrucciones
    val items = listOf(
        Item(text = gameInstructions1, image = R.drawable.inst_1),
        Item(text = gameInstructions2, image = R.drawable.inst_2),
        Item(text = gameInstructions3, image = R.drawable.inst_3),
        Item(text = gameInstructions4, image = R.drawable.inst_4),
        Item(text = gameInstructions5, image = R.drawable.inst_5),
        Item(text = gameInstructions6, image = R.drawable.rnrlogo),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = BACKGROUND_COLOR)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tittleInstructions,
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

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .background(Color(SEC_BACKGROUND_COLOR))
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.80f)
        ) {
            items(items) { item ->
                ItemRow(item)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Boton volver a pantalla Home
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp),
            colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR)),
            onClick = { navigateToOptionsScreen() }
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

@Composable
fun ItemRow(item: Item) {
    Column(
        modifier = Modifier
            .padding(all = 10.dp)
            .background(Color(SEC_BACKGROUND_COLOR))
    ) {
        Text(
            text = item.text,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            modifier = Modifier
                .height(80.dp)
                .width(180.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

data class Item(
    val text: String,
    val image: Int
)

@Composable
@Preview
fun InstructionsScreenPreview() {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    RustAndRevoltTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = LocalContentColor.current
        ) {
            InstructionsScreen(
                navigateToOptionsScreen = {},
            )
        }
    }
}
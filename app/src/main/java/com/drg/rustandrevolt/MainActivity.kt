package com.drg.rustandrevolt

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.drg.rustandrevolt.sqlite.PlayerDBScheme
import com.drg.rustandrevolt.sqlite.PlayerDbHelper
import com.drg.rustandrevolt.ui.navigation.AppNavigation
import com.drg.rustandrevolt.ui.theme.RustAndRevoltTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inicializar SQLite
        val playerDbHelper = PlayerDbHelper(this)
        val writablePlayerDbHelper = playerDbHelper.readableDatabase

        // Eliminar todas las filas de la tabla
        writablePlayerDbHelper.delete(PlayerDBScheme.TABLE_NAME, null, null)

        val values = ContentValues().apply {
            put(PlayerDBScheme.COLUMN_ID, 777)
            put(PlayerDBScheme.COLUMN_NAME, "Player 777")
            put(PlayerDBScheme.COLUMN_SCORE, 0)
        }
        writablePlayerDbHelper.insert(PlayerDBScheme.TABLE_NAME, null, values)

        //**************************************
        // Consultar la base de datos para recuperar los datos del jugador
        val cursor = writablePlayerDbHelper.query(
            PlayerDBScheme.TABLE_NAME,
            arrayOf(PlayerDBScheme.COLUMN_NAME, PlayerDBScheme.COLUMN_SCORE),
            null, null, null, null, null
        )

        // Verificar si el cursor contiene datos
        if (cursor.moveToFirst()) {
            do {
                // Verificar si la columna existe en el cursor antes de acceder a ella
                val nameIndex = cursor.getColumnIndex(PlayerDBScheme.COLUMN_NAME)
                val scoreIndex = cursor.getColumnIndex(PlayerDBScheme.COLUMN_SCORE)

                if (nameIndex >= 0 && scoreIndex >= 0) {
                    val playerName = cursor.getString(nameIndex)
                    val playerScore = cursor.getInt(scoreIndex)
                    Log.d("PlayerInfo", "Player Name: $playerName, Score: $playerScore")
                }
            } while (cursor.moveToNext())
        }

        // Cerrar el cursor y la base de datos después de recuperar los datos
        cursor.close()
        //**************************************

        setContent {
            RustAndRevoltTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background
                ) {
                    //LLamada a la función de navegación de pantallas
                    AppNavigation()
                }
            }
        }
    }
}
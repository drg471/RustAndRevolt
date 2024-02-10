package com.drg.rustandrevolt.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.drg.rustandrevolt.domain.Player
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences

class PlayerDao {

    companion object {
        lateinit var writablePlayerDbHelper: SQLiteDatabase

        object PlayerEntity {
            var id: Int = 0
            var nameP: String = ""
            var score: Int = 0
        }
    }



    fun initSqLite(context: Context) {
        //Inicializar SQLite
        val playerDbHelper = PlayerDbHelper(context)
        writablePlayerDbHelper = playerDbHelper.readableDatabase
    }

    fun deleteDB() {
        // Eliminar todas las filas de la tabla
        writablePlayerDbHelper.delete(PlayerDBScheme.TABLE_NAME, null, null)
    }

    fun savePlayer(player: Player) {
        player.id = 888
        player.score = 0

        val values = ContentValues().apply {
            put(PlayerDBScheme.COLUMN_ID, player.id)
            put(PlayerDBScheme.COLUMN_NAME, player.name)
            put(PlayerDBScheme.COLUMN_SCORE, player.score)
        }
        writablePlayerDbHelper.insert(PlayerDBScheme.TABLE_NAME, null, values)
    }

    fun getPlayer(): PlayerEntity {
        // Consultar la base de datos para recuperar los datos del jugador
        val cursor = writablePlayerDbHelper.query(
            PlayerDBScheme.TABLE_NAME,
            arrayOf(PlayerDBScheme.COLUMN_ID, PlayerDBScheme.COLUMN_NAME, PlayerDBScheme.COLUMN_SCORE),
            null, null, null, null, null
        )

        // Verificar si el cursor contiene datos
        if (cursor.moveToFirst()) {
            do {
                // Verificar si la columna existe en el cursor antes de acceder a ella
                val idIndex = cursor.getColumnIndex(PlayerDBScheme.COLUMN_ID)
                val nameIndex = cursor.getColumnIndex(PlayerDBScheme.COLUMN_NAME)
                val scoreIndex = cursor.getColumnIndex(PlayerDBScheme.COLUMN_SCORE)

                if (nameIndex >= 0 && scoreIndex >= 0 && idIndex >= 0) {
                    val playerId = cursor.getInt(idIndex)
                    val playerName = cursor.getString(nameIndex)
                    val playerScore = cursor.getInt(scoreIndex)

                    Log.d(
                        "PlayerInfo",
                        "Player Name: $playerName, Score: $playerScore, Id: $playerId"
                    )

                    PlayerEntity.id = playerId
                    PlayerEntity.nameP = playerName
                    PlayerEntity.score = playerScore
                }
            } while (cursor.moveToNext())
        }

        // Cerrar el cursor
        cursor.close()

        return PlayerEntity
    }

}
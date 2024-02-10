package com.drg.rustandrevolt.repositories

import android.content.Context
import com.drg.rustandrevolt.domain.Player
import com.drg.rustandrevolt.sqlite.PlayerDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepository @Inject constructor() {

    private var player : Player? = null
    private val playerDao: PlayerDao = PlayerDao()
    companion object{
        lateinit var context: Context
    }

    fun setPlayer() {
        playerDao.initSqLite(context)
        playerDao.deleteDB()
        playerDao.savePlayer(Player("Player ONE"))

        var playerEntity = playerDao.getPlayer()
        this.player = Player(playerEntity.nameP)
        this.player!!.id = playerEntity.id
        this.player!!.score = playerEntity.score
    }

    fun getPlayer() : Player{
        if (player == null){
            setPlayer()
        }
        return player!!
    }
}
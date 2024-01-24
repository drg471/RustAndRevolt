package com.drg.rustandrevolt.entities

class Player (name : String) {
    companion object{
        val player : Player = Player("Player1")
    }
    private val id : String = ""
    private var score : Int = 0
    var currentGameCharacter : Character = Rebel("default")
        get() = field
        set(value){
            field = value
        }

    fun getInstance() : Player {
        return player
    }
}
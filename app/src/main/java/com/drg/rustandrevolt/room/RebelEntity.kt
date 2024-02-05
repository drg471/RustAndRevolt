package com.drg.rustandrevolt.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.drg.rustandrevolt.domain.Rebel

@Entity
class RebelEntity (
    @PrimaryKey
    val name : String,
    @ColumnInfo(name = "imageCardResource") val imageCardResource: String,
    @ColumnInfo(name = "imageCombatPlayerResource") val imageCombatPlayerResource: String,
    @ColumnInfo(name = "imageCombatEnemyResource") val imageCombatEnemyResource: String,
)

fun RebelEntity.toDomain() = Rebel(
    name = name,
    imageCardResource = imageCardResource,
    imageCombatPlayerResource = imageCombatPlayerResource,
    imageCombatEnemyResource = imageCombatEnemyResource
)
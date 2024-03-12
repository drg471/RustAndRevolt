package com.drg.rustandrevolt.data.sources.sqlite

object PlayerDBScheme {
    const val TABLE_NAME = "player"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_SCORE = "score"
}

const val SQL_CREATE_ENTRIES = """
    CREATE TABLE ${PlayerDBScheme.TABLE_NAME} (
            ${PlayerDBScheme.COLUMN_ID} INTEGER PRIMARY KEY,
            ${PlayerDBScheme.COLUMN_NAME} TEXT,
            ${PlayerDBScheme.COLUMN_SCORE} INTEGER
    )"""


const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${PlayerDBScheme.TABLE_NAME}"
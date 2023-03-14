package com.konden.freedom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class EntityWord(
    @PrimaryKey(autoGenerate = true)
    val id: Int  =0,
    val number_id: Int,
    val word: String
)
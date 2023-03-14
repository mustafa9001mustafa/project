package com.konden.freedom.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY id ASC")
    fun getAlphabetizedWords(): LiveData<List<EntityWord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: EntityWord)

    @Query("DELETE FROM word_table")
     fun deleteAll()
}
//package com.konden.freedom.test.database
//
//import androidx.annotation.WorkerThread
//import androidx.lifecycle.LiveData
//import kotlinx.coroutines.flow.Flow
//
//class WordRepository(private val wordDao: WordDao) {
//    val allWords: LiveData<List<EntityWord>> = wordDao.getAlphabetizedWords()
//
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun insert(word: EntityWord) {
//        wordDao.insert(word)
//    }
//}
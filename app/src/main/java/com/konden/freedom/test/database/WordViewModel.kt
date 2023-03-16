//package com.konden.freedom.test.database
//
//import android.app.Application
//import androidx.lifecycle.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class WordViewModel(application: Application) : AndroidViewModel(application) {
//    private val read_all_data: LiveData<List<EntityWord>>
//    private val repository: WordRepository
//
//    init {
//        val data_dao = WordRoomDatabase.getDatabase(application).wordDao()
//        repository = WordRepository(data_dao)
//        read_all_data = repository.allWords
//    }
//
//
//    fun add(entityWord: EntityWord) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.insert(entityWord)
//        }
//    }
//}
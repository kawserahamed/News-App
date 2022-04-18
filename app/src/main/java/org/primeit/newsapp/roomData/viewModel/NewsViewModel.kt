package org.primeit.newsapp.roomData.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.primeit.newsapp.roomData.database.NewsDatabase
import org.primeit.newsapp.roomData.repository.NewsRepository
import org.primeit.newsapp.roomData.roomModel.RoomModel

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val allNews: LiveData<List<RoomModel>>
    private val repository: NewsRepository

    init {
        val dao = NewsDatabase.getDatabase(application).getNewsDao()
        repository = NewsRepository(dao)
        allNews = repository.allNews
    }


    fun deleteNews(news: RoomModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(news)

    }

    fun updateNews(news: RoomModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(news)

    }

    fun addNews(news: RoomModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(news)

    }
}
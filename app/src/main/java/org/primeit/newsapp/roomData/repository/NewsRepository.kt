package org.primeit.newsapp.roomData.repository

import androidx.lifecycle.LiveData
import org.primeit.newsapp.roomData.database.NewsDao
import org.primeit.newsapp.roomData.roomModel.RoomModel

class NewsRepository(private val noteDao: NewsDao) {

    val allNews: LiveData<List<RoomModel>> = noteDao.getAllNotes()


    suspend fun insert(news: RoomModel) {
        noteDao.insert(news)
    }

    suspend fun update(news: RoomModel) {
        noteDao.update(news)
    }

    suspend fun delete(news: RoomModel) {
        noteDao.delete(news)
    }
}
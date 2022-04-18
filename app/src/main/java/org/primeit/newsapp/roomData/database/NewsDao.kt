package org.primeit.newsapp.roomData.database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.primeit.newsapp.model.NewsModel
import org.primeit.newsapp.roomData.roomModel.RoomModel


@Dao
interface NewsDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(news: RoomModel)


    @Update
    suspend fun update(news: RoomModel)

    @Delete
    suspend fun delete(news: RoomModel)


    @Query("Select*from news_table order by id ASC")
    fun getAllNotes(): LiveData<List<RoomModel>>
}
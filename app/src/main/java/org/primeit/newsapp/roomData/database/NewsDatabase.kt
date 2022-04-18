package org.primeit.newsapp.roomData.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.primeit.newsapp.roomData.roomModel.RoomModel

@Database(entities = [RoomModel::class], version = 1, exportSchema = false)
abstract  class NewsDatabase: RoomDatabase() {

    abstract fun getNewsDao(): NewsDao


    companion object {

        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
package org.primeit.newsapp.roomData.roomModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news_table")
class RoomModel(
    @ColumnInfo(name = "title") var newsTitle: String = "",
    @ColumnInfo(name = "author") var newsAuthor: String = "",
    @ColumnInfo(name = "url") var newsUrl: String = "",
    @ColumnInfo(name = "imageUrl") var newsImageUrl: String = ""
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
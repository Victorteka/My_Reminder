package victorteka.github.io.myreminder.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey @ColumnInfo(name = "note_title") val title: String,
    @ColumnInfo(name = "note_body") val noteBody: String
)
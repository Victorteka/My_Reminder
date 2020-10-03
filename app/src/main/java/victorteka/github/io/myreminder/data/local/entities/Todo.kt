package victorteka.github.io.myreminder.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey @ColumnInfo(name = "todo") val todo: String,
    @ColumnInfo(name = "isDone") val isDone: Int
)

package victorteka.github.io.myreminder.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fitness")
data class Fitness(
    @PrimaryKey @ColumnInfo(name = "exercise") val exercise: String
)
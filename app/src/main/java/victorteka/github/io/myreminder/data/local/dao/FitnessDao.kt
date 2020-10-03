package victorteka.github.io.myreminder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import victorteka.github.io.myreminder.data.local.entities.Fitness

@Dao
interface FitnessDao {
    @Query("SELECT * FROM fitness")
    suspend fun getAllExercise():List<Fitness>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(fitness: Fitness)
}
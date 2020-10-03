package victorteka.github.io.myreminder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import victorteka.github.io.myreminder.data.local.dao.FitnessDao
import victorteka.github.io.myreminder.data.local.dao.NoteDao
import victorteka.github.io.myreminder.data.local.dao.TodoDao
import victorteka.github.io.myreminder.data.local.entities.Fitness
import victorteka.github.io.myreminder.data.local.entities.Note
import victorteka.github.io.myreminder.data.local.entities.Todo

@Database(entities = [Note::class, Todo::class, Fitness::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao():NoteDao
    abstract fun todoDao(): TodoDao
    abstract fun fitnessDao():FitnessDao

}
package victorteka.github.io.myreminder.data.local

import victorteka.github.io.myreminder.data.local.entities.Fitness
import victorteka.github.io.myreminder.data.local.entities.Note
import victorteka.github.io.myreminder.data.local.entities.Todo

interface DatabaseHelper {
    suspend fun getNotes(): List<Note>

    suspend fun insertNotes(note: Note)

    suspend fun updateNote(newTitle: String,newBody: String, noteId: String)

    suspend fun getTodos(): List<Todo>

    suspend fun insertTodo(todo: Todo)

    suspend fun updateStatus(status:Int, todoUpdated: String)

    suspend fun getDoneTodo():List<Todo>

    suspend fun getExercise():List<Fitness>

    suspend fun insertExercise(fitness: Fitness)
}
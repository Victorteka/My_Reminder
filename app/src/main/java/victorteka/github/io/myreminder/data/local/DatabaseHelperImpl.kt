package victorteka.github.io.myreminder.data.local

import victorteka.github.io.myreminder.data.local.entities.Fitness
import victorteka.github.io.myreminder.data.local.entities.Note
import victorteka.github.io.myreminder.data.local.entities.Todo

class DatabaseHelperImpl(private val appDatabase: AppDatabase): DatabaseHelper {
    override suspend fun getNotes(): List<Note> = appDatabase.noteDao().getAllNotes()

    override suspend fun insertNotes(note: Note) = appDatabase.noteDao().addNote(note)

    override suspend fun updateNote(newTitle: String, newBody: String, noteId: String) = appDatabase
        .noteDao().updateNote(newTitle, newBody, noteId)

    override suspend fun getTodos(): List<Todo> = appDatabase.todoDao().getAllTodos()

    override suspend fun insertTodo(todo: Todo) = appDatabase.todoDao().addTodo(todo)

    override suspend fun updateStatus(status: Int, todoUpdated: String) = appDatabase.todoDao()
        .updateStatus(status,todoUpdated)

    override suspend fun getDoneTodo(): List<Todo> = appDatabase.todoDao().getDoneTodo()

    override suspend fun getExercise(): List<Fitness> = appDatabase.fitnessDao().getAllExercise()

    override suspend fun insertExercise(fitness: Fitness) = appDatabase.fitnessDao().insertExercise(fitness)
}
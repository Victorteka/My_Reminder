package victorteka.github.io.myreminder.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import victorteka.github.io.myreminder.data.local.DatabaseHelper
import victorteka.github.io.myreminder.ui.addNote.viewmodels.AddNoteViewModel
import victorteka.github.io.myreminder.ui.fitness.viewmodels.FitnessViewModel
import victorteka.github.io.myreminder.ui.notes.viewmodels.NotesViewModel
import victorteka.github.io.myreminder.ui.todos.viewmodels.TodoViewModel

class ViewModelFactory(private val dbHelper: DatabaseHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)){
            return AddNoteViewModel(dbHelper) as T
        }
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)){
            return NotesViewModel(dbHelper) as T
        }
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)){
            return TodoViewModel(dbHelper) as T
        }
        if (modelClass.isAssignableFrom(FitnessViewModel::class.java)){
            return FitnessViewModel(dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
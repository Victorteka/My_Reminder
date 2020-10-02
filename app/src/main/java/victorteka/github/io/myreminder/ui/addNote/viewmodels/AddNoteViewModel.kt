package victorteka.github.io.myreminder.ui.addNote.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import victorteka.github.io.myreminder.data.local.DatabaseHelper
import victorteka.github.io.myreminder.data.local.entities.Note
import java.lang.Exception

class AddNoteViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {

    fun addNewNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        try {
            dbHelper.insertNotes(note)

        } catch (e: Exception) {
            Log.d("TAG", "---error---" + e.message)
        }
    }

}
package victorteka.github.io.myreminder.ui.notes.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import victorteka.github.io.myreminder.data.local.DatabaseHelper
import victorteka.github.io.myreminder.data.local.entities.Note
import java.lang.Exception

class NotesViewModel(private val dbHelper: DatabaseHelper): ViewModel() {
    private val notes = MutableLiveData<List<Note>>()

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            try {
                val notesFromDb = dbHelper.getNotes()
                notes.postValue(notesFromDb)
            }catch (e: Exception){
                Log.d("TAG", "--error---"+e.message)
            }
        }
    }

    fun updateNote(title: String, body: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dbHelper.updateNote(title, body, title)
                Log.d("TAG", "===successful note update===")
            }catch (e: Exception){
                Log.d("TAG", "===fail to update note=== ${e.message}")
            }
        }
    }

    fun getNotes(): LiveData<List<Note>>{
        return notes
    }
}
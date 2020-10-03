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
    private val _notes = MutableLiveData<List<Note>>()
    val notes :LiveData<List<Note>>
        get() = _notes


    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            try {
                val notesFromDb = dbHelper.getNotes()
                _notes.postValue(notesFromDb)
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

//    fun getNotes(): LiveData<List<Note>>{
//        return _notes
//    }

    fun isNotesListEmpty():Int{
        return _notes.value.orEmpty().size
    }
}
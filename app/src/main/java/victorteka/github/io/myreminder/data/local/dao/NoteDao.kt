package victorteka.github.io.myreminder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import victorteka.github.io.myreminder.data.local.entities.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes():List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Query("UPDATE notes SET note_title= :newTitle, note_body= :newBody WHERE note_title= :noteId")
    suspend fun updateNote(newTitle: String,newBody: String, noteId: String)
}
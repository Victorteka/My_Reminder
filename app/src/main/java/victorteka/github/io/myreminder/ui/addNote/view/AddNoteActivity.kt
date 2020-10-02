package victorteka.github.io.myreminder.ui.addNote.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_note.*
import victorteka.github.io.myreminder.MainActivity
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.DatabaseBuilder
import victorteka.github.io.myreminder.data.local.DatabaseHelperImpl
import victorteka.github.io.myreminder.data.local.entities.Note
import victorteka.github.io.myreminder.ui.addNote.viewmodels.AddNoteViewModel
import victorteka.github.io.myreminder.utils.ViewModelFactory

class AddNoteActivity : AppCompatActivity() {

    private lateinit var viewModel: AddNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupViewModel()
        addNoteBtn.setOnClickListener {
            addNoteToLocalDb()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)))
        ).get(AddNoteViewModel::class.java)
    }

    private fun addNoteToLocalDb() {
        val title = noteTitle.text.toString()
        val body = noteBody.text.toString()
        Log.d("TAG", "addNoteToLocalDb: $title")
        Log.d("TAG", "addNoteToLocalDb: $body")
        val newNote = Note(title, body)

        viewModel.addNewNote(newNote)
        val intent = Intent(this@AddNoteActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
package victorteka.github.io.myreminder.ui.notes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_edit_note.*
import victorteka.github.io.myreminder.MainActivity
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.DatabaseBuilder
import victorteka.github.io.myreminder.data.local.DatabaseHelperImpl
import victorteka.github.io.myreminder.ui.notes.viewmodels.NotesViewModel
import victorteka.github.io.myreminder.utils.ViewModelFactory

class EditNoteActivity : AppCompatActivity() {

    private lateinit var viewModel:NotesViewModel
    private lateinit var newTitle: String
    private lateinit var newNoteBody: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val extras = intent.extras
        if (extras != null){
            newTitle = extras.getString("noteTitle")!!
            newNoteBody = extras.getString("noteBody")!!
            editNoteTitle.setText(newTitle)
            editNoteBody.setText(newNoteBody)
        }

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(NotesViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.submitEditedNote ->{
                viewModel.updateNote(editNoteTitle.text.toString(), editNoteBody.text.toString())
                Log.d("TAG", "onOptionsItemSelected: === ${editNoteBody.text.toString()}")
                val intent = Intent(this@EditNoteActivity, MainActivity::class.java)
                Toast.makeText(this, "Note updated successful", Toast.LENGTH_LONG).show()
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
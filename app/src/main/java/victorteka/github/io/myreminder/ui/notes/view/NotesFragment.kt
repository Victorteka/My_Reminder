package victorteka.github.io.myreminder.ui.notes.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_notes.*
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.DatabaseBuilder
import victorteka.github.io.myreminder.data.local.DatabaseHelperImpl
import victorteka.github.io.myreminder.data.local.entities.Note
import victorteka.github.io.myreminder.ui.addNote.view.AddNoteActivity
import victorteka.github.io.myreminder.ui.notes.adapters.NoteListener
import victorteka.github.io.myreminder.ui.notes.adapters.NotesAdapter
import victorteka.github.io.myreminder.ui.notes.viewmodels.NotesViewModel
import victorteka.github.io.myreminder.utils.ViewModelFactory
import kotlin.math.log

class NotesFragment : Fragment() {

    private lateinit var adapter: NotesAdapter
    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        setupViewModel()
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            renderList(it)
            Log.d("TAG", "-----onViewCreated----: $it")
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openAddNote.setOnClickListener {
            navigateToAddNote()
        }
        setupUI()
       val nList = viewModel.notes.value.orEmpty()
        if (nList.isNotEmpty()){
            noNotes.visibility = View.GONE
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                DatabaseHelperImpl(DatabaseBuilder.getInstance(requireActivity().applicationContext))
            )
        ).get(NotesViewModel::class.java)
        viewModel.isNotesListEmpty()
    }

    private fun setupUI() {
        notesRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NotesAdapter(
            arrayListOf(),
            NoteListener {
                val intent = Intent(requireContext(), EditNoteActivity::class.java)
                intent.putExtra("noteTitle", it.title)
                intent.putExtra("noteBody", it.noteBody)
                startActivity(intent)

            }
        )
        notesRecyclerView.adapter = adapter

    }

    private fun renderList(notes: List<Note>) {
        adapter.addNotes(notes)
        adapter.notifyDataSetChanged()
    }

    private fun navigateToAddNote() {
        val intent = Intent(activity, AddNoteActivity::class.java)
        val options = this.activity?.let {
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                it, openAddNote, "shared_element_end_root"
            )
        }
        startActivity(intent)
    }


}
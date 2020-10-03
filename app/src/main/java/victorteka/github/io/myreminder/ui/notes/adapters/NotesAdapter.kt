package victorteka.github.io.myreminder.ui.notes.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.entities.Note
import victorteka.github.io.myreminder.databinding.NoteItemBinding

class NotesAdapter(private val notes:ArrayList<Note>, private val clickListener: NoteListener): RecyclerView.Adapter<NotesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note, clickListener: NoteListener){
            binding.note = note
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }
        companion object{
            fun from(parent: ViewGroup):DataViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
                return DataViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder.from(parent)

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(notes[position], clickListener)
    }

    fun addNotes(list: List<Note>){
        notes.addAll(list)
    }
}

class NoteListener(val clickLister:(note: Note)-> Unit){
    fun onClick(note: Note) = clickLister(note)
}
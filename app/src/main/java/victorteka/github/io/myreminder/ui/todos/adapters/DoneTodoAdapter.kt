package victorteka.github.io.myreminder.ui.todos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.done_todo_item.view.*
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.entities.Todo

class DoneTodoAdapter(private val doneTodos: ArrayList<Todo>): RecyclerView.Adapter<DoneTodoAdapter.DoneTodoViewHolder>() {

    class DoneTodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(todo: Todo){
            itemView.doneCheckedTextView.text = todo.todo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneTodoViewHolder {
        return DoneTodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.done_todo_item, parent, false)
        )
    }

    override fun getItemCount() = doneTodos.size

    override fun onBindViewHolder(holder: DoneTodoViewHolder, position: Int) {
        holder.bind(doneTodos[position])
    }

    fun addDoneTodo(list: List<Todo>){
        doneTodos.addAll(list)
    }
}
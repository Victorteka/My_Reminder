package victorteka.github.io.myreminder.ui.todos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.entities.Todo

class TodoAdapter(private val todos: ArrayList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: Todo) {
            itemView.todoCheckBox.text = todo.todo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        )
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    fun addTodos(list: List<Todo>){
        todos.addAll(list)
    }
}
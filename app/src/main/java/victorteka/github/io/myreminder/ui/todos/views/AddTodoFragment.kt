package victorteka.github.io.myreminder.ui.todos.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import kotlinx.android.synthetic.main.fragment_add_todo.*
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.DatabaseBuilder
import victorteka.github.io.myreminder.data.local.DatabaseHelperImpl
import victorteka.github.io.myreminder.data.local.entities.Todo
import victorteka.github.io.myreminder.ui.todos.viewmodels.TodoViewModel
import victorteka.github.io.myreminder.utils.ViewModelFactory

class AddTodoFragment : SuperBottomSheetFragment() {

    private lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()

        sendBtn.setOnClickListener {
            addTodoToLocalDb()
        }
    }

    private fun addTodoToLocalDb() {
        val newTodo = todoEditText.text.toString()
        Log.d("TAG", "addTodoToLocalDb: $newTodo")
        viewModel.addNewTodo(Todo(newTodo, 0))
        
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                DatabaseHelperImpl(DatabaseBuilder.getInstance(requireActivity().applicationContext))
            )
        ).get(TodoViewModel::class.java)
    }


    override fun getPeekHeight(): Int {
        return 200
    }
}
package victorteka.github.io.myreminder.ui.todos.views

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlinx.android.synthetic.main.fragment_todo.view.*
import kotlinx.android.synthetic.main.todo_item.*
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.DatabaseBuilder
import victorteka.github.io.myreminder.data.local.DatabaseHelperImpl
import victorteka.github.io.myreminder.data.local.entities.Todo
import victorteka.github.io.myreminder.ui.todos.adapters.DoneTodoAdapter
import victorteka.github.io.myreminder.ui.todos.adapters.TodoAdapter
import victorteka.github.io.myreminder.ui.todos.viewmodels.TodoViewModel
import victorteka.github.io.myreminder.utils.ViewModelFactory
import kotlin.math.log

class TodoFragment : Fragment() {

    private lateinit var viewModel: TodoViewModel
    private lateinit var adapter: TodoAdapter
    private lateinit var doneAdapter:DoneTodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo, container, false)
        setupViewModel()
        viewModel.getAllTodos().observe(viewLifecycleOwner, Observer {
            renderList(it)
        })
        viewModel.getDoneTodos().observe(viewLifecycleOwner, Observer {
            renderDoneList(it)
        })
        return view
    }

    private fun renderDoneList(list: List<Todo>) {
        doneAdapter.addDoneTodo(list)
        doneAdapter.notifyDataSetChanged()
    }

    private fun renderList(list: List<Todo>) {
        adapter.addTodos(list)
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openBottomSheet()
        setupUI()

        Handler().postDelayed(
            {
                        todoCheckBox?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                            if (isChecked){
                                updateTodo(buttonView.text.toString())
                                viewModel.getDoneTodos().observe(viewLifecycleOwner, Observer {
                                    setupViewModel()
                                    setupUI()
                                    renderDoneList(it)
                                    Log.d("TAG", "====onViewCreated:====${it.size}")
                                })
                            }
                        })
            },2000
        )
    }

    private fun updateTodo(todo:String) {
            viewModel.updateStatus(1, todo)
    }

    private fun setupUI() {
        todoRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TodoAdapter(arrayListOf())
        todoRecyclerView.adapter = adapter
        doneTodoRecyclerView.layoutManager = LinearLayoutManager(context)
        doneAdapter = DoneTodoAdapter(arrayListOf())
        doneTodoRecyclerView.adapter = doneAdapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getInstance(requireActivity().applicationContext)))
        ).get(TodoViewModel::class.java)
    }

    private fun openBottomSheet() {
        todoFab.setOnClickListener {
            val sheet  = AddTodoFragment()
            sheet.show(requireActivity().supportFragmentManager, "AddTodoBottomSheet")
        }
    }


}
package victorteka.github.io.myreminder.ui.todos.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import victorteka.github.io.myreminder.data.local.DatabaseHelper
import victorteka.github.io.myreminder.data.local.entities.Todo
import java.lang.Exception

class TodoViewModel(private val dbHelper: DatabaseHelper): ViewModel() {

    private val todos = MutableLiveData<List<Todo>>()
    private val doneTodos = MutableLiveData<List<Todo>>()

    init {
        fetchTodos()
        fetchDoneTodo()
    }

    private fun fetchDoneTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
               val doneTodoFromDb = dbHelper.getDoneTodo()
                doneTodos.postValue(doneTodoFromDb)
            }catch (e: Exception){
                Log.d("TAG", "---fail to get done todos---"+e.message)
            }
        }
    }

    private fun fetchTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val todosFromDb = dbHelper.getTodos()
                todos.postValue(todosFromDb)
            }catch (e:Exception){
                Log.d("TAG", "---cannot get todos cos--"+e.message)
            }
        }
    }

    fun addNewTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        try {
            dbHelper.insertTodo(todo)
        }catch (e: Exception){
            Log.d("TAG", "---error---"+e.message)
        }
    }

    fun updateStatus(status: Int, todo : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dbHelper.updateStatus(status, todo)
                Log.d("TAG", "---todo updated----")
            }catch (e: Exception){
                Log.d("TAG", "---fail to update-- ${e.message}")
            }
        }
    }

    fun getAllTodos():LiveData<List<Todo>>{
        return todos
    }

    fun getDoneTodos():LiveData<List<Todo>>{
        return doneTodos
    }
}
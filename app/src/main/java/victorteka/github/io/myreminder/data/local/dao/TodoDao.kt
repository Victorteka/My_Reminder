package victorteka.github.io.myreminder.data.local.dao

import androidx.room.*
import victorteka.github.io.myreminder.data.local.entities.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos  WHERE isDone=0")
    suspend fun getAllTodos():List<Todo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: Todo)

    @Query("DELETE FROM todos")
    suspend fun clearTodoTable()

    @Query("SELECT * FROM todos WHERE isDone=1")
    suspend fun getDoneTodo():List<Todo>

    @Query("UPDATE todos SET isDone=:status WHERE todo=:todo")
    suspend fun updateStatus(status:Int, todo: String)
}
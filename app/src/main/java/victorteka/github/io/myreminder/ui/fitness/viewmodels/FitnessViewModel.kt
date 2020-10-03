package victorteka.github.io.myreminder.ui.fitness.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import victorteka.github.io.myreminder.data.local.DatabaseHelper
import victorteka.github.io.myreminder.data.local.entities.Fitness
import java.lang.Exception

class FitnessViewModel(private val dbHelper: DatabaseHelper): ViewModel() {

    private val exercises = MutableLiveData<List<Fitness>>()

    init {
        fetchExercise()
    }

    private fun fetchExercise() {
        viewModelScope.launch {
            try {
                val exerciseFromDb = dbHelper.getExercise()
                exercises.postValue(exerciseFromDb)
                Log.d("TAG", "---exercise fetched--- $exerciseFromDb")
            }catch (e: Exception){
                Log.d("TAG", "---error couldn't fetch exercise--- ${e.message}")
            }
        }
    }

    fun addExercise(fitness: Fitness){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dbHelper.insertExercise(fitness)
                Log.d("TAG", "---successfully added---")
            }catch (e:Exception){
                Log.d("TAG", "---error---${e.message}")
            }
        }
    }

    fun getExercise():LiveData<List<Fitness>> = exercises
}
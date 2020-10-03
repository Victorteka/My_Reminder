package victorteka.github.io.myreminder.ui.fitness.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_item.view.*
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.entities.Fitness

class FitnessAdapter(private val exercises: ArrayList<Fitness>): RecyclerView.Adapter<FitnessAdapter.FitnessDataViewHolder>(){
        class FitnessDataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun bind(fitness: Fitness){
                itemView.todoCheckBox.text = fitness.exercise
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitnessDataViewHolder {
        return FitnessDataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        )
    }

    override fun getItemCount() = exercises.size

    override fun onBindViewHolder(holder: FitnessDataViewHolder, position: Int) {
        holder.bind(exercises[position])
    }

    fun addExercise(list: List<Fitness>){
        exercises.addAll(list)
    }

}
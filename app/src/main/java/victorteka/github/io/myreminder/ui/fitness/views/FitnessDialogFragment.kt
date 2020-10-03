package victorteka.github.io.myreminder.ui.fitness.views

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import victorteka.github.io.myreminder.data.local.DatabaseBuilder
import victorteka.github.io.myreminder.data.local.DatabaseHelperImpl
import victorteka.github.io.myreminder.data.local.entities.Fitness
import victorteka.github.io.myreminder.ui.fitness.viewmodels.FitnessViewModel
import victorteka.github.io.myreminder.utils.ViewModelFactory

class FitnessDialogFragment: DialogFragment() {

    private lateinit var viewModel: FitnessViewModel

    private val multiItems = arrayOf("Jump rope", "Running/jogging", "Dancing",
        "Aerobic strength circuit","Stair mill/stair stepper","Swimming","Stationary bike", "Elliptical","Cardio dance class")
    private val selectedItems = ArrayList<Int>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setupViewModel()
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Start Fitness")
            .setMultiChoiceItems(multiItems, null){ dialog: DialogInterface, which: Int, isChecked: Boolean ->
                if (isChecked){
                    selectedItems.add(which)
                }else if (selectedItems.contains(which)){
                    selectedItems.remove(Integer.valueOf(which))
                }
            }.setPositiveButton("Create"){ dialog, which ->
                for (item in selectedItems){
                    viewModel.addExercise(Fitness(multiItems[item]))
                }
            }
            .setNegativeButton("Cancel"){ dialog, which ->

            }
            .create()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext().applicationContext)))
        ).get(FitnessViewModel::class.java)
    }
}
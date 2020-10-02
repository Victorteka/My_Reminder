package victorteka.github.io.myreminder.ui.fitness.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_fitness.*
import victorteka.github.io.myreminder.R
import victorteka.github.io.myreminder.data.local.DatabaseBuilder
import victorteka.github.io.myreminder.data.local.DatabaseHelperImpl
import victorteka.github.io.myreminder.data.local.entities.Fitness
import victorteka.github.io.myreminder.ui.fitness.adapters.FitnessAdapter
import victorteka.github.io.myreminder.ui.fitness.viewmodels.FitnessViewModel
import victorteka.github.io.myreminder.utils.ViewModelFactory

class FitnessFragment : Fragment() {

    private lateinit var viewModel: FitnessViewModel
    private lateinit var adapter: FitnessAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setTheme(R.style.AppTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fitness, container, false)
        setHasOptionsMenu(true)
        setupViewModel()
        viewModel.getExercise().observe(viewLifecycleOwner, Observer {
            renderList(it)
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        return inflater.inflate(R.menu.fitness_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addNewFitness -> {
                FitnessDialogFragment().show(requireFragmentManager(), "TAG")
                return true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun renderList(list: List<Fitness>) {
        adapter.addExercise(list)
        adapter.notifyDataSetChanged()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext().applicationContext)))
        ).get(FitnessViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        if (viewModel.getExercise().value != null){
            setupUI()
        }else{
            fitnessImageView.visibility = View.GONE
            startFitnessBtn.visibility = View.GONE
            fitnessWelcomeText.visibility = View.GONE
            startFitnessBtn.setOnClickListener {
                FitnessDialogFragment().show(requireFragmentManager(), "TAG")
            }
        }


    }

    private fun setupUI() {
        fitnessRecyclerView.visibility = View.VISIBLE
        adapter = FitnessAdapter(
            arrayListOf()
        )
        fitnessRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fitnessRecyclerView.adapter = adapter
    }
}
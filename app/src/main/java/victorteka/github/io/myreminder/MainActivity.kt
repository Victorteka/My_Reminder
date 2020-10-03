package victorteka.github.io.myreminder

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout_custom.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(
            R.color.textTitle
        ))

       drawerLayout = findViewById(R.id.navigation_layout)
        val navView: NavigationView = findViewById(R.id.navigation_view)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration.Builder(
                R.id.notesFragment, R.id.todayFragment, R.id.todoFragment,
                R.id.settingsFragment, R.id.notificationFragment
        ).setDrawerLayout(drawerLayout).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        navigation_view.setNavigationItemSelectedListener(this);
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.todayFragment, R.id.fitnessFragment,
                R.id.settingsFragment, R.id.notificationFragment -> {
                    bottom_navigation.visibility = View.GONE
                    drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    toolbar?.visibility = View.VISIBLE
                    bottom_navigation.visibility = View.VISIBLE
                    drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            // Set up navigation here
            R.id.notes -> findNavController(R.id.nav_host_fragment).navigate(R.id.notesFragment)
            R.id.todos_fragment -> findNavController(R.id.nav_host_fragment).navigate(R.id.todoFragment)
        }
        false
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
        drawerLayout.closeDrawers()
        val id = menuItem.itemId
        when(id){
            R.id.today -> findNavController(R.id.nav_host_fragment).navigate(R.id.todoFragment)
            R.id.settings -> findNavController(R.id.nav_host_fragment).navigate(R.id.settingsFragment)
            R.id.notification -> findNavController(R.id.nav_host_fragment).navigate(R.id.notificationFragment)
            R.id.fitness -> findNavController(R.id.nav_host_fragment).navigate(R.id.fitnessFragment)
        }
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
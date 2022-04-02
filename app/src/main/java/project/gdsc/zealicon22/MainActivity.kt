package project.gdsc.zealicon22

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import project.gdsc.zealicon22.databinding.ActivityMainBinding

/**
 * @author Dheeraj Kotwani on 23/02/22.
 */
class MainActivity : AppCompatActivity(), DuoMenuView.OnMenuClickListener {

    private lateinit var duoAdapter: DuoMenuAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleMenu()

    }

    private fun handleMenu() {
        val menuOptions: ArrayList<String> = ArrayList()
        menuOptions.add(getString(R.string.home))
        menuOptions.add(getString(R.string.reach))
        menuOptions.add(getString(R.string.team))
        menuOptions.add(getString(R.string.about))

        duoAdapter = DuoMenuAdapter(menuOptions)
        binding.duoMenuView.adapter = duoAdapter
        binding.duoMenuView.setOnMenuClickListener(this)
        duoAdapter.setViewSelected(0, true)
        binding.duoDrawerLayout.openDrawer()

    }

    override fun onFooterClicked() {

    }

    override fun onHeaderClicked() {

    }

    override fun onOptionClicked(position: Int, objectClicked: Any?) {

        binding.duoDrawerLayout.closeDrawer()
        duoAdapter.setViewSelected(position, true)

        when (position) {
            0 -> {
                // TODO handle case for home screen
                binding.mainConstraintLayout.setBackgroundColor(Color.WHITE)
            }
            1 -> {
                // TODO handle case for reach us
                binding.mainConstraintLayout.setBackgroundColor(Color.RED)
            }
            2 -> {
                // TODO handle case for team
                binding.mainConstraintLayout.setBackgroundColor(Color.GREEN)
            }
            3 -> {
                // TODO handle case for about
                binding.mainConstraintLayout.setBackgroundColor(Color.YELLOW)
            }
        }

    }

}
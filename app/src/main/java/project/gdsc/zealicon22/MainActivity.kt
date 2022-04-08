package project.gdsc.zealicon22

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import project.gdsc.zealicon22.SearchEvents.SearchEventsFragment
import project.gdsc.zealicon22.databinding.ActivityMainBinding
import project.gdsc.zealicon22.home.HomeFragment
import project.gdsc.zealicon22.myevents.MyEventsFragment
import project.gdsc.zealicon22.signup.SignupFragment

/**
 * @author Dheeraj Kotwani on 23/02/22.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DuoMenuView.OnMenuClickListener {

    private lateinit var duoAdapter: DuoMenuAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        handleMenu()
        setupBottomNav()
        setSelectedPageData()
        setupClickListener()
    }

    private fun setupBottomNav() {

        binding.bottomNavBar.apply {
            itemIconTintList = null

            setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.home_screen -> {
                        supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, HomeFragment()).commit()
                    }
                    R.id.search_screen -> {
//                        supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, ).commit()
                    }
                    R.id.my_events_screen -> {
                        supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, MyEventsFragment()).commit()
                    }
                }
                return@setOnItemSelectedListener true
            }

        }

    }

    private fun setupClickListener() {
        binding.buttonMenu.setOnClickListener {
            binding.duoDrawerLayout.openDrawer()
        }
    }

    private fun setSelectedPageData() {
        duoAdapter.setViewSelected(0, true)
        supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, HomeFragment()).commit()
    }

    private fun handleMenu() {
        val menuOptions: ArrayList<String> = ArrayList()
        menuOptions.add(getString(R.string.home))
        menuOptions.add(getString(R.string.reach))
        menuOptions.add(getString(R.string.team))
        menuOptions.add(getString(R.string.about))
        menuOptions.add(getString(R.string.sign_up)) // TODO remove after testing
        menuOptions.add("Event Detail") // TODO (remove) after setting up navigation
        menuOptions.add(getString(R.string.day_one)) // TODO remove after testing
        menuOptions.add("Search") // TODO remove after setting up bottom navigation

        duoAdapter = DuoMenuAdapter(menuOptions)
        binding.duoMenuView.adapter = duoAdapter
        binding.duoMenuView.setOnMenuClickListener(this)
        duoAdapter.setViewSelected(0, true)

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
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, HomeFragment()).commit()
            }
            1 -> {
                // TODO handle case for reach us
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, ReachFragment()).commit()
            }
            2 -> {
                // TODO handle case for team
                binding.mainConstraintLayout.setBackgroundColor(Color.GREEN)
            }
            3 -> {
                // TODO handle case for about
                binding.mainConstraintLayout.setBackgroundColor(Color.YELLOW)
            }
            4 -> {
                // TODO added code to navigate to SignupFragment (remove on testing)
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, SignupFragment()).commit()
            }
            5 -> {
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, EventDetailsFragment()).commit()
            }
            6-> {
                // TODO added code to navigate to SignupFragment (remove on testing)
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, DayWiseEventsFragment()).commit()
            }
            7 -> {
                supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, SearchEventsFragment()).commit()
            }
        }

    }

}
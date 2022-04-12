package project.gdsc.zealicon22

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import project.gdsc.zealicon22.about.AboutFragment
import project.gdsc.zealicon22.databinding.ActivityMainBinding
import project.gdsc.zealicon22.di.AppModule
import project.gdsc.zealicon22.home.HomeFragment
import project.gdsc.zealicon22.models.PaymentSuccess
import project.gdsc.zealicon22.myevents.MyEventsFragment
import project.gdsc.zealicon22.reach.ReachFragment
import project.gdsc.zealicon22.search_events.SearchEventsFragment
import project.gdsc.zealicon22.signup.RegisterFragment
import project.gdsc.zealicon22.teams.TeamsFragment

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
                        supportFragmentManager.beginTransaction()
                            .replace(binding.mainFrame.id, HomeFragment()).commit()
                    }
                    R.id.search_screen -> {
                        supportFragmentManager.beginTransaction()
                            .replace(binding.mainFrame.id, SearchEventsFragment()).commit()
                    }
                    R.id.my_events_screen -> {
                        supportFragmentManager.beginTransaction()
                            .replace(binding.mainFrame.id, MyEventsFragment()).commit()
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
        val sf = AppModule.provideSharedPreferences(this)
        if (sf.contains("USER_DATA")) {
            duoAdapter.setViewSelected(0, true)
            val sp = AppModule.provideSharedPreferences(this)
            val userData = sp.getString("USER_DATA", "")
            val paymentSuccess = Gson().fromJson<PaymentSuccess>(userData, PaymentSuccess::class.java)
            Toast.makeText(this, "Welcome, ${paymentSuccess.fullname}", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, HomeFragment())
            .commit()
        }
        else {
            duoAdapter.setViewSelected(4, true)
            binding.bottomNavBar.visibility = View.GONE
            supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, RegisterFragment()).commit()
        }
    }

    private fun handleMenu() {
        val menuOptions: ArrayList<String> = ArrayList()
        menuOptions.add(getString(R.string.home))
        menuOptions.add(getString(R.string.reach))
        menuOptions.add(getString(R.string.team))
        menuOptions.add(getString(R.string.about))
        menuOptions.add(getString(R.string.sign_up)) // TODO remove after testing

        duoAdapter = DuoMenuAdapter(menuOptions)
        binding.duoMenuView.adapter = duoAdapter
        binding.duoMenuView.setOnMenuClickListener(this)

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
                binding.bottomNavBar.visibility = View.VISIBLE
                binding.pageTitle.text = getString(R.string.title_discover)
                binding.bottomNavBar.selectedItemId = R.id.home_screen
                supportFragmentManager.beginTransaction()
                    .replace(binding.mainFrame.id, HomeFragment()).commit()
            }
            1 -> {
                // TODO handle case for reach us
                binding.bottomNavBar.visibility = View.GONE
                binding.pageTitle.text = getString(R.string.reach)
                supportFragmentManager.beginTransaction()
                    .replace(binding.mainFrame.id, ReachFragment()).commit()
            }
            2 -> {
                // TODO handle case for team
                binding.bottomNavBar.visibility = View.GONE
                binding.pageTitle.text = getString(R.string.team)
                supportFragmentManager.beginTransaction()
                    .replace(binding.mainFrame.id, TeamsFragment()).commit()
            }
            3 -> {
                // handle case for about
                binding.bottomNavBar.visibility = View.GONE
                binding.pageTitle.text = getString(R.string.about)
                supportFragmentManager.beginTransaction()
                    .replace(binding.mainFrame.id, AboutFragment()).commit()
            }
            4 -> {
                // navigate to SignupFragment
                binding.bottomNavBar.visibility = View.GONE
                binding.pageTitle.text = ""
                supportFragmentManager.beginTransaction()
                    .replace(binding.mainFrame.id, RegisterFragment()).commit()
            }
        }

    }

}
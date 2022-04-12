package project.gdsc.zealicon22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import project.gdsc.zealicon22.databinding.ActivityDetailBinding
import project.gdsc.zealicon22.dayWiseEvent.DayWiseEventsFragment
import project.gdsc.zealicon22.models.Events
import timber.log.Timber

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        showRequiredFragment()

    }

    private fun showRequiredFragment() {

        when (intent.getStringExtra("fragment_to_show")) {
            "event_day" -> {
                val day = intent.getIntExtra("day", 4) // Use day as argument to DayWiseEventFragment
                supportFragmentManager.beginTransaction().replace(binding.detailFrame.id, DayWiseEventsFragment()).commit()
            }
            "event_detail" -> {
                val events = Gson().fromJson(intent.getStringExtra("EVENT_DETAIL"), Events::class.java)
                Timber.d("Events $events")
                if (events != null) {
                    val frag = EventDetailsFragment.newInstance(intent.getStringExtra("EVENT_DETAIL"))
                    supportFragmentManager.beginTransaction()
                        .replace(binding.detailFrame.id, frag).commit()
                }
            }
        }
    }
}
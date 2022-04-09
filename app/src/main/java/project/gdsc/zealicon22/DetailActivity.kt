package project.gdsc.zealicon22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import project.gdsc.zealicon22.databinding.ActivityDetailBinding
import project.gdsc.zealicon22.dayWiseEvent.DayWiseEventsFragment

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
                supportFragmentManager.beginTransaction().replace(binding.detailFrame.id, EventDetailsFragment()).commit()
            }
        }

    }
}
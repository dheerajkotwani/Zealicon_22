package project.gdsc.zealicon22

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import project.gdsc.zealicon22.databinding.FragmentEventDetailsBinding
import project.gdsc.zealicon22.databinding.ItemEventDetailUnitBinding

class EventDetailsFragment : Fragment() {

    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var event: TestEvent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getEventData()
        showEventData()

    }

    private fun getEventData() {

        event = TestEvent(
            getString(R.string.example_event_name),
            getString(R.string.example_event_desc),
            getString(R.string.example_event_category),
            getString(R.string.example_society),
            getString(R.string.date_27),
            getString(R.string.example_event_location),
            getString(R.string.example_event_time),
            getString(R.string.example_event_phone),
            getString(R.string.example_event_rules),
            getString(R.string.example_event_prize),
            getString(R.string.example_event_contact)
        )

    }

    private fun showEventData() {

        binding.eventName.text = event.eventName
        binding.eventCategory.text = event.eventCategory
        binding.eventSociety.text = event.eventSociety
        binding.eventCalendar.apply {
            setIcon(this, R.drawable.ic_calendar)
            eventUnitInfo.text = event.eventDate
        }
        binding.eventLocation.apply {
            setIcon(this, R.drawable.ic_location)
            eventUnitInfo.text = event.eventLocation
        }
        binding.eventClock.apply {
            setIcon(this, R.drawable.ic_clock)
            eventUnitInfo.text = event.eventTime
        }
        binding.eventPhone.apply {
            setIcon(this, R.drawable.ic_phone)
            eventUnitInfo.text = event.eventPersonPhone
        }
        binding.eventInfo.text = event.eventDescription
        binding.eventRules.text = event.eventRules
        binding.eventPrize.text = event.eventPrizes
        binding.eventContact.text = event.eventContact

    }

    private fun setIcon(item: ItemEventDetailUnitBinding, drawableId: Int) {
        item.eventUnitInfoIcon.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                drawableId,
                null
            )
        )
    }

    // TODO (remove) This is for testing only
    data class TestEvent(
        val eventName: String,
        val eventDescription: String,
        val eventCategory: String,
        val eventSociety: String,
        val eventDate: String,
        val eventLocation: String,
        val eventTime: String,
        val eventPersonPhone: String,
        val eventRules: String,
        val eventPrizes: String,
        val eventContact: String
    )

}
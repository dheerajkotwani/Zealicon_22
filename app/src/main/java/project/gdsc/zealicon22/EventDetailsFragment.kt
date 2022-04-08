package project.gdsc.zealicon22

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.gdsc.zealicon22.databinding.FragmentEventDetailsBinding
import project.gdsc.zealicon22.databinding.FragmentMyEventsBinding
import java.util.*

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
            getString(R.string.example_event_desc)
        )

    }

    private fun showEventData() {

        binding.eventName.text = event.eventName

    }

    // TODO (remove) This is for testing only
    data class TestEvent(
        val eventName: String,
        val eventDescription: String
    )

}
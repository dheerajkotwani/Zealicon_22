package project.gdsc.zealicon22

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import project.gdsc.zealicon22.databinding.FragmentEventDetailsBinding
import project.gdsc.zealicon22.databinding.ItemEventDetailUnitBinding
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.ResultHandler
import timber.log.Timber
import javax.inject.Inject
import project.gdsc.zealicon22.utils.formatTo
import project.gdsc.zealicon22.utils.getDateTime

@AndroidEntryPoint
class EventDetailsFragment : Fragment() {

    private var _binding: FragmentEventDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sp: SharedPreferences

    private val viewModel by activityViewModels<EventViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showEventData()
    }

    private fun showEventData() {
        val events = Gson().fromJson((arguments?.getString(EVENT_DETAILS)), Events::class.java)
        binding.eventName.text = events.name
        binding.eventCategory.text = events.category
        binding.eventSociety.text = events.society
        binding.eventCalendar.apply {
            setIcon(this, R.drawable.ic_calendar)
            eventUnitInfo.text = getDateTime(events.datetime).formatTo("d MMM , YYYY")
        }
        binding.eventLocation.apply {
            setIcon(this, R.drawable.ic_location)
            eventUnitInfo.text = events.venue
        }
        binding.eventClock.apply {
            setIcon(this, R.drawable.ic_clock)
            eventUnitInfo.text = getDateTime(events.datetime).formatTo("HH:mm") + " HOURS"
        }
        binding.eventPhone.apply {
            setIcon(this, R.drawable.ic_phone)
            eventUnitInfo.text = events.contact?.userdetails?.contact_no
            eventUnitInfo.setOnClickListener {
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("tel:" + events.contact?.userdetails?.contact_no!!)
                        )
                    )
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }
        binding.eventInfo.text = events.description
        binding.eventRules.text = events.rules
        binding.eventPrize.text = events.prizes
        binding.eventContact.text =
            "${events.contact?.first_name} ${events.contact?.last_name} : ${events.contact?.userdetails?.contact_no}"

        handleEventRegistration(events)
    }

    private fun handleEventRegistration(events: Events) {
        if (sp.getBoolean("EventId:${events.id}", false))
            binding.registerButton.visibility = View.GONE
        else {
            binding.registerButton.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    viewModel.registerForEvent(events.id.toString())
                }
            }
        }

        viewModel.registration.observe(viewLifecycleOwner) {
            when (it) {
                is ResultHandler.Loading -> {
                    binding.registerButton.visibility = View.GONE
                    binding.loading.visibility = View.VISIBLE
                }
                is ResultHandler.Failure -> {
                    binding.registerButton.visibility = View.VISIBLE
                    binding.loading.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Oops! Something went wrong.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Timber.e("RequestFailure: ${it.message}")
                }
                is ResultHandler.Success -> {
                    binding.registerButton.visibility = View.GONE
                    binding.loading.visibility = View.GONE
                    Toast.makeText(requireContext(), "Registered successfully!", Toast.LENGTH_SHORT)
                        .show()
                    sp.edit().putBoolean("EventId:${events.id}", true).apply()
                }
            }
        }
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

    companion object {
        const val EVENT_DETAILS = "EVENT_DETAILS"
        fun newInstance(events: String?): EventDetailsFragment {
            return EventDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(EVENT_DETAILS, events)
                }
            }
        }
    }
}
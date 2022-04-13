package project.gdsc.zealicon22.my_events

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import project.gdsc.zealicon22.DetailActivity
import project.gdsc.zealicon22.MainViewModel
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.adapter.EventsAdapter
import project.gdsc.zealicon22.databinding.FragmentMyEventsBinding
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.models.MyEvents
import project.gdsc.zealicon22.models.ResultHandler
import javax.inject.Inject
import project.gdsc.zealicon22.utils.showToast

/**
 * @Updated: Karan Verma on 13/04/22
 */
@AndroidEntryPoint
class MyEventsFragment : Fragment() {

    private var _binding: FragmentMyEventsBinding? = null
    private val binding get() = _binding!!

    private var eventsAdapter: EventsAdapter? = null

    private val viewModel: MainViewModel by activityViewModels()

    @Inject
    lateinit var sp: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyEventsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setupMyEventsRecycler()
        observer()

    }

    private fun observer() {
        viewModel.myEvents.observe(viewLifecycleOwner){
            when (it) {
                is ResultHandler.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is ResultHandler.Success -> {
                    binding.loading.visibility = View.GONE
                    if (!it.result.isNullOrEmpty()){
                        binding.noEventsText.visibility = View.GONE
                        eventsAdapter?.setList(it.result as ArrayList<MyEvents>)
                    } else binding.noEventsText.visibility = View.VISIBLE
                }
                is ResultHandler.Failure -> {
                    binding.loading.visibility = View.GONE
                    binding.noEventsText.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Oops! Something went wrong.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getData() {
        if (sp.getString("ZEAL_ID", "").isNullOrBlank()){
            binding.noEventsText.visibility = View.VISIBLE
            binding.noEventsText.text = "Please login to see your events !"
        } else {
            viewModel.getMyEvents()
        }

    }

    private val onEventCardClick: (event: Events) -> Unit = {

        val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
            // Pass event data from here, and then to fragment as argument
            putExtra("fragment_to_show", "event_detail")
            putExtra("EVENT_DETAIL", Gson().toJson(it).toString())
        }
        startActivity(intent)

    }

    private fun setupMyEventsRecycler() {
        eventsAdapter = EventsAdapter(requireContext(), onEventCardClick)
        binding.myEventsRecycler.adapter = eventsAdapter
    }
}

package project.gdsc.zealicon22.myevents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.adapter.EventsAdapter
import project.gdsc.zealicon22.databinding.FragmentMyEventsBinding

class MyEventsFragment : Fragment() {

    private var _binding: FragmentMyEventsBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventsAdapter: EventsAdapter

    private val sampleEventNameList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyEventsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSampleData()
        setupMyEventsRecycler()

    }

    private fun setSampleData() {

        sampleEventNameList.add("Event One")
        sampleEventNameList.add("Event Two")
        sampleEventNameList.add("Event Three")
        sampleEventNameList.add("Event Four")
        sampleEventNameList.add("Event Five")
        sampleEventNameList.add("Event Six")
        sampleEventNameList.add("Event Seven")
        sampleEventNameList.add("Event Eight")
        sampleEventNameList.add("Event Nine")
        sampleEventNameList.add("Event Ten")
        sampleEventNameList.add("Event Eleven")

    }


    private fun setupMyEventsRecycler() {

        eventsAdapter = EventsAdapter(sampleEventNameList, requireContext())
        binding.myEventsRecycler.adapter = eventsAdapter

    }


}
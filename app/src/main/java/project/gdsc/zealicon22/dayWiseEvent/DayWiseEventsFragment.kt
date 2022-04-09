package project.gdsc.zealicon22.dayWiseEvent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scaleMatrix
import androidx.recyclerview.widget.LinearLayoutManager
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.search_events.SearchEventsAdapter
import project.gdsc.zealicon22.databinding.FragmentDayWiseEventsBinding

/**
 * @author Anuraj Jain on 07/04/22.
 */
class DayWiseEventsFragment : Fragment() {

    private var _binding: FragmentDayWiseEventsBinding? = null
    private val binding get() = _binding!!
    private var eventsAdapter : SearchEventsAdapter? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDayWiseEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dayOne.apply {
            root.rotation = -5f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic1, null))
            day.text = getString(R.string.day_one)
            scaleMatrix(sx = 0.8f, sy=0.8f)
        }

        eventsAdapter = SearchEventsAdapter()
        binding.comicsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventsAdapter
        }
        setData()
    }

    private fun setData() {
//        eventsAdapter?.setList(eventsList)
        binding.dayCount.text = getString(R.string.day_one)
        binding.eventDate.text = "26 Apr 2022"
        binding.comicCounts.text = "27 COMICS • 3 CATEGORIES"
    }
}
package project.gdsc.zealicon22.dayWiseEvent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scaleMatrix
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import project.gdsc.zealicon22.DetailActivity
import project.gdsc.zealicon22.MainViewModel
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentDayWiseEventsBinding
import project.gdsc.zealicon22.models.Events
import project.gdsc.zealicon22.search_events.SearchEventsAdapter
import project.gdsc.zealicon22.utils.formatTo
import project.gdsc.zealicon22.utils.getDateTime

/**
 * @author Anuraj Jain on 07/04/22.
 */
class DayWiseEventsFragment : Fragment() {

    private var _binding: FragmentDayWiseEventsBinding? = null
    private val binding get() = _binding!!
    private var eventsAdapter = SearchEventsAdapter()
    private val viewModel by activityViewModels<MainViewModel>()
    private var day: Int? = null

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
        
        binding.eventDate.visibility =if(viewModel.mDay.value == null) View.INVISIBLE else View.VISIBLE

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
        setObservers()
        setListners()
    }

    private fun setListners() {
        eventsAdapter?.onItemClick = {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra("EVENT_DETAIL", Gson().toJson(it).toString())
            intent.putExtra("fragment_to_show", "event_detail")
            startActivity(intent)
        }
    }

    private fun setObservers() {
        viewModel.selectedDay.observe(viewLifecycleOwner){
           eventsAdapter.setList(it)
            setData(it)
        }
    }

    private fun setData(list: List<Events>) {
        var dayCount : String? = null
        day = viewModel.mDay.value
        val categories = getCategories(list)
        when(day){
            1 -> {
                dayCount = getString(R.string.day_one)
                binding.dayOne.apply {
                    image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic1, null))
                    day.text = getString(R.string.day_one)
                    scaleMatrix(sx = 0.8f, sy = 0.8f)
                }
            }
            2 -> {
                dayCount = getString(R.string.day_two)
                binding.dayOne.apply {
                    image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic2, null))
                    day.text = getString(R.string.day_two)
                    scaleMatrix(sx = 0.8f, sy = 0.8f)
                }
            }
            3 -> {
                dayCount = getString(R.string.day_three)
                binding.dayOne.apply {
                    image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic3, null))
                    day.text = getString(R.string.day_three)
                    scaleMatrix(sx = 0.8f, sy = 0.8f)
                }
            }
            else -> {
                dayCount = getString(R.string.day_four)
                binding.dayOne.apply {
                    image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic4, null))
                    day.text = getString(R.string.day_four)
                    scaleMatrix(sx = 0.8f, sy = 0.8f)
                }
            }
        }
        binding.dayCount.text = dayCount
        if (!list.isNullOrEmpty()) {
            binding.eventDate.text = getDateTime(list[0].datetime).formatTo("dd MMM yyyy")
        }
        binding.comicCounts.text = "${list.size} COMICS • ${categories.size} CATEGORIES"
    }

    private fun getCategories(list: List<Events>): ArrayList<String> {
        val categories : ArrayList<String> = arrayListOf()
        for (event in list){
            if (categories.contains(event.category))
                continue
            else
                categories.add(event.category)
        }
        return categories
    }


}
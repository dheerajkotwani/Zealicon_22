package project.gdsc.zealicon22.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scaleMatrix
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import project.gdsc.zealicon22.DetailActivity
import project.gdsc.zealicon22.MainViewModel
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentHomeBinding
import project.gdsc.zealicon22.dayWiseEvent.DayWiseEventsFragment
import project.gdsc.zealicon22.models.ResultHandler
import project.gdsc.zealicon22.utils.Loading
import timber.log.Timber

/**
 * Created by Nakul
 * on 4,April,2022
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private val eventsAdapter: UpcomingEventsAdapter by lazy {
        UpcomingEventsAdapter {
            startActivity(Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("EVENT_DETAIL", Gson().toJson(it).toString())
                putExtra("fragment_to_show","event_detail")
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setExploreEvents()
        setClickListener()
        setUpcomingEvents()
        if (!viewModel.subscribed)
            subscribeUI()
    }

    private fun setUpcomingEvents() {
        binding.upcomingEventsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventsAdapter
        }
    }

    private fun subscribeUI() {

        viewModel.subscribed = true

        val loading = object : Loading(requireContext()) {
            override val backAction: () -> Unit
                get() = {
                    dismiss()
                    activity?.finish()
                }
        }
        loading.create()

        viewModel.upcomingEvents.observe(viewLifecycleOwner) {
            when (it) {
                is ResultHandler.Loading -> {
                    loading.show()
                }
                is ResultHandler.Success -> {
                    loading.dismissWithAnim()
                    eventsAdapter.setList(it.result)
                }
                is ResultHandler.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Oops! Something went wrong.",
                        Toast.LENGTH_SHORT
                    ).show()
                    loading.dismissWithAnim()
                }
            }
        }
    }

    private fun setExploreEvents() {
        binding.dayOne.apply {
            root.rotation = -5f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic1, null))
            day.text = getString(R.string.day_one)
            scaleMatrix(sx = 0.8f, sy = 0.8f)
        }
        binding.dayTwo.apply {
            root.rotation = 1f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic2, null))
            day.text = getString(R.string.day_two)
            scaleMatrix(sx = 0.8f, sy = 0.8f)
        }
        binding.dayThree.apply {
            root.rotation = 4f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic3, null))
            day.text = getString(R.string.day_three)
            scaleMatrix(sx = 0.8f, sy = 0.8f)
        }
        binding.dayFour.apply {
            root.rotation = -4f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic4, null))
            day.text = getString(R.string.day_four)
            scaleMatrix(sx = 0.8f, sy = 0.8f)
        }
    }

    private fun setClickListener() {
        binding.dayOne.root.setOnClickListener {
            viewModel.selectDay(1)
            navigateToEventWiseFragment()
        }
        binding.dayTwo.root.setOnClickListener {
            viewModel.selectDay(2)
            navigateToEventWiseFragment()
        }
        binding.dayThree.root.setOnClickListener {
            viewModel.selectDay(3)
            navigateToEventWiseFragment()
        }
    }

    private fun navigateToEventWiseFragment() {
        activity?.supportFragmentManager?.popBackStack()
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.mainFrame, DayWiseEventsFragment())?.addToBackStack("Home")?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.subscribed = false
    }

}
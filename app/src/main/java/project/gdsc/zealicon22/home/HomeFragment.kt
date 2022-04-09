package project.gdsc.zealicon22.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scaleMatrix
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import project.gdsc.zealicon22.MainViewModel
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentHomeBinding
import project.gdsc.zealicon22.dayWiseEvent.DayWiseEventsFragment
import project.gdsc.zealicon22.models.ResultHandler
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
        if (!viewModel.subscribed)
            subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.subscribed = true
        viewModel.events.observe(viewLifecycleOwner) {
            when(it) {
                is ResultHandler.Loading -> {
//                    TODO()
                    Timber.d("RequestingRightNow")
                }
                is ResultHandler.Success -> {
                    Timber.d("SuccessRequest: ${it.result}")
                }
                is ResultHandler.Failure -> {
                    Timber.e("FailureRequest: ${it.message}")
                }
            }
        }
    }

    private fun setExploreEvents() {
        binding.dayOne.apply {
            root.rotation = -5f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic1, null))
            day.text = getString(R.string.day_one)
            scaleMatrix(sx = 0.8f, sy=0.8f)
        }
        binding.dayTwo.apply {
            root.rotation = 1f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic2, null))
            day.text = getString(R.string.day_two)
            scaleMatrix(sx = 0.8f, sy=0.8f)
        }
        binding.dayThree.apply {
            root.rotation = 4f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic3, null))
            day.text = getString(R.string.day_three)
            scaleMatrix(sx = 0.8f, sy=0.8f)
        }
        binding.dayFour.apply {
            root.rotation = -4f
            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic4, null))
            day.text = getString(R.string.day_four)
            scaleMatrix(sx = 0.8f, sy=0.8f)
        }
    }

    private fun setClickListener() {
        binding.dayOne.root.setOnClickListener {
            viewModel.selectDay(1)
        }
        binding.dayTwo.root.setOnClickListener {
            viewModel.selectDay(2)
        }
        binding.dayThree.root.setOnClickListener {
            viewModel.selectDay(3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
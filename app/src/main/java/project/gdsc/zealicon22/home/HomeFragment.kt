package project.gdsc.zealicon22.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scaleMatrix
import androidx.fragment.app.Fragment
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentHomeBinding

/**
 * Created by Nakul
 * on 4,April,2022
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

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

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
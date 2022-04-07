package project.gdsc.zealicon22

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scaleMatrix
import project.gdsc.zealicon22.databinding.FragmentDayWiseEventsBinding
import project.gdsc.zealicon22.databinding.FragmentHomeBinding

/**
 * @author Dheeraj Kotwani on 06/04/22.
 */
class DayWiseEventsFragment : Fragment() {

    private var _binding: FragmentDayWiseEventsBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.dayOne.apply {
            root.rotation = -5f
//            image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.comic1, null))
            day.text = getString(R.string.day_one)
//            scaleMatrix(sx = 0.8f, sy=0.8f)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDayWiseEventsBinding.inflate(inflater, container, false)
        return binding.root
    }


}
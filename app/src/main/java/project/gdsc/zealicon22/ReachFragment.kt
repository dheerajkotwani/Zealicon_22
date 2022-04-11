package project.gdsc.zealicon22

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.gdsc.zealicon22.databinding.FragmentReachBinding

/**
 * @author Dheeraj Kotwani on 07/04/22.
 */
class ReachFragment : Fragment() {

    private var _binding: FragmentReachBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReachBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardMap.setOnClickListener {

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
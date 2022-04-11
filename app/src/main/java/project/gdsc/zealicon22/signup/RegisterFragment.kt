package project.gdsc.zealicon22.signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentRegisterBinding
import project.gdsc.zealicon22.databinding.FragmentSecondBinding

/**
 * @author Dheeraj Kotwani on 12/04/22.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardCreateId.cardIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_add))
        binding.cardSearch.cardIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_search))

        binding.cardCreateId.textCardTitle.text = "CREATE ZEAL ID"
        binding.cardSearch.textCardTitle.text = "FIND ZEAL ID"

        binding.cardSearch.root.setOnClickListener {

        }

        binding.cardCreateId.root.setOnClickListener {

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
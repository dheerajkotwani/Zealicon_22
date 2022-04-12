package project.gdsc.zealicon22.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.RegisterViewModel
import project.gdsc.zealicon22.SignupActivity
import project.gdsc.zealicon22.databinding.FragmentRegisterBinding
import project.gdsc.zealicon22.databinding.FragmentSecondBinding

/**
 * @author Dheeraj Kotwani on 12/04/22.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<RegisterViewModel>()
    private val dialog =  FindZealIdDialog(requireContext(), viewModel).apply { create() }

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

        binding.cardCreateId.root.setOnClickListener {
            startActivity(Intent(requireContext(), SignupActivity::class.java))
        }

        binding.cardSearch.root.setOnClickListener {
            dialog.show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
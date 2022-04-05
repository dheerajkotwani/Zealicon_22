package project.gdsc.zealicon22.loginsignup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!

    // checks to true or false according to visible fragment on the screen
    private var isLoginActive = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {

        binding.btLogin.apply {
            actionText.apply {
                text = getString(R.string.login)
            }
            buttonConst.apply {
                setOnClickListener(loginClickListener)
            }
        }
        binding.btSignup.apply {
            actionText.apply {
                text = getString(R.string.sign_up)
            }
            buttonConst.apply {
                setOnClickListener(signupClickListener)
            }
        }

    }

    private val loginClickListener: (view: View) -> Unit = {



    }

    private val signupClickListener: (view: View) -> Unit = {



    }

}
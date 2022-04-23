package project.gdsc.zealicon22.signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import project.gdsc.zealicon22.MainActivity
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentReachBinding
import project.gdsc.zealicon22.databinding.FragmentZealIdBinding
import project.gdsc.zealicon22.di.AppModule
import project.gdsc.zealicon22.models.PaymentSuccess

class ZealIdFragment : Fragment() {

    private var _binding: FragmentZealIdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentZealIdBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sp = AppModule.provideSharedPreferences(requireContext())
        val userData = sp.getString("USER_DATA", "")

        val paymentSuccess = Gson().fromJson<PaymentSuccess>(userData, PaymentSuccess::class.java)

        binding.textZealId.text = paymentSuccess.zeal_id
        binding.textHeyUser.text = "Hey, ${paymentSuccess.fullname.substringBefore(" ")}"

        MainActivity.justRegistered = true

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
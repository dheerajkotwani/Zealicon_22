package project.gdsc.zealicon22

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import dagger.hilt.android.AndroidEntryPoint
import project.gdsc.zealicon22.databinding.ActivitySignupBinding
import project.gdsc.zealicon22.signup.SignupFragment
import timber.log.Timber

@AndroidEntryPoint
class SignupActivity : AppCompatActivity(), PaymentResultWithDataListener {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(binding.signupFrame.id, SignupFragment()).commit()
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Timber.d("Payment successful $p1")
        finish()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Timber.d("Payment failed $p1")
    }
}
package project.gdsc.zealicon22

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import dagger.hilt.android.AndroidEntryPoint
import project.gdsc.zealicon22.databinding.ActivitySignupBinding
import project.gdsc.zealicon22.models.PaymentReceipt
import project.gdsc.zealicon22.models.ResultHandler
import project.gdsc.zealicon22.signup.SignupFragment
import timber.log.Timber

@AndroidEntryPoint
class SignupActivity : AppCompatActivity(), PaymentResultWithDataListener {
    private lateinit var binding: ActivitySignupBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(binding.signupFrame.id, SignupFragment()).commit()
        binding.backBtn.setOnClickListener { onBackPressed() }

    }


    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        try {
            Timber.d("Payment successful ${p1?.data}")
            val data = p1?.data
            var paymentReceipt: PaymentReceipt? = null
            val frag = supportFragmentManager.findFragmentById(R.id.signupFrame)
            if (frag is SignupFragment) {
                paymentReceipt = frag.getReceiptData()
            }
            Timber.d("Payment successful Id ${data?.getString("razorpay_order_id")}")
            paymentReceipt?.razorpay_order_id = data?.getString("razorpay_order_id")
            paymentReceipt?.razorpay_payment_id = data?.getString("razorpay_payment_id")
            paymentReceipt?.razorpay_signature = data?.getString("razorpay_signature")
            Timber.d("Payment receipt ${paymentReceipt}")
            if (paymentReceipt != null) {
                viewModel.submitReceipt(paymentReceipt)
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }catch (e: Exception){
            Timber.d("Error success payment: $e")
        }
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Timber.d("Payment failed $p1")

    }

}

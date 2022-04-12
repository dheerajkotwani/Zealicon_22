package project.gdsc.zealicon22.signup

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import com.razorpay.Checkout
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.RegisterViewModel
import project.gdsc.zealicon22.SignupActivity
import project.gdsc.zealicon22.databinding.FragmentSignupBinding
import project.gdsc.zealicon22.databinding.ItemAvatarBinding
import project.gdsc.zealicon22.di.AppModule
import project.gdsc.zealicon22.models.PaymentReceipt
import project.gdsc.zealicon22.models.PaymentResponse
import project.gdsc.zealicon22.models.PaymentSuccess
import project.gdsc.zealicon22.models.ResultHandler
import project.gdsc.zealicon22.utils.animateOnClick
import project.gdsc.zealicon22.utils.animateToRemoveErrorMessage
import project.gdsc.zealicon22.utils.animateToShowErrorMessage
import timber.log.Timber


@AndroidEntryPoint
class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by activityViewModels()


    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var selectedAvatarPosition = 1

    private var avatarBindingList: ArrayList<ItemAvatarBinding> = ArrayList()

    private var nameError: Boolean = false
    private var emailError: Boolean = false
    private var phoneError: Boolean = false
    private var admNumberError: Boolean = false
    private lateinit var paymentResponse: PaymentResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButton()
        setUpObserver()
        setupEditTexts()
        setupAvatars()
        Checkout.preload(activity?.applicationContext)
    }

    private fun setupButton() {

        binding.btSignup.apply {
            setOnClickListener {
                animateOnClick(it)
                handleSignUp()
            }
        }

    }

    private fun setUpObserver() {
        viewModel.orderId.observe(viewLifecycleOwner) {
            when (it) {
                is ResultHandler.Loading -> {
                    Timber.d("RequestingRightNow")
                }
                is ResultHandler.Success -> {
                    Timber.d("SuccessRequest: ${it.result}")
                    paymentResponse = it.result
                    initiatePayment(it.result)

                }
                is ResultHandler.Failure -> {
                    Timber.e("FailureRequest: ${it.message}")
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.submitReceipt.observe(viewLifecycleOwner){

            when (it) {
                is ResultHandler.Loading -> {
                    Timber.d("RequestingRightNow")
                }
                is ResultHandler.Success -> {
                    Timber.d("SuccessRequest: ${it.result}")
                    val sp = AppModule.provideSharedPreferences(requireContext())
                    sp.edit().putString("USER_DATA", Gson().toJson(it.result)).apply()
                    sp.edit().putString("ZEAL_ID", it.result.zeal_id).apply()

                    Timber.d("SuccessRequest SharedPref: ${sp.getString("USER_DATA", "")}")

                    //open ZealId screen after successful registration
                }
                is ResultHandler.Failure -> {
                    Timber.e("FailureRequest kk: ${it.message}")
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getOrderId() {
        viewModel.getOrderId()
    }

    private fun handleSignUp() {

        if (haveInputErrors()) return

        getOrderId()
    }

    private fun haveInputErrors(): Boolean {
        val name = binding.etName.input.text.toString()
        if (name.isEmpty() || name.length <= 3) {
            nameError = true
            disableSignupButton()
            animateToShowErrorMessage(binding.etName.errorCard)
            return true
        }

        val email = binding.etEmail.input.text.toString()
        if (!email.matches(emailPattern.toRegex())) {
            emailError = true
            disableSignupButton()
            animateToShowErrorMessage(binding.etEmail.errorCard)
            return true
        }

        val phoneNumber = binding.etPhone.input.text.toString()
        if (phoneNumber.length != 10 || !phoneNumber.all { char -> char.isDigit() }) {
            phoneError = true
            disableSignupButton()
            animateToShowErrorMessage(binding.etPhone.errorCard)
            return true
        }

        val admissionNumber = binding.etAdmNumber.input.text.toString()
        if (admissionNumber.length < 7) {
            admNumberError = true
            disableSignupButton()
            animateToShowErrorMessage(binding.etAdmNumber.errorCard)
            return true
        }
        return false
    }

    private fun clearErrors() {
        if (nameError) {
            animateToRemoveErrorMessage(binding.etName.errorCard)
            nameError = false
        }
        if (emailError) {
            animateToRemoveErrorMessage(binding.etEmail.errorCard)
            emailError = false
        }
        if (phoneError) {
            animateToRemoveErrorMessage(binding.etPhone.errorCard)
            phoneError = false
        }
        if (admNumberError) {
            animateToRemoveErrorMessage(binding.etAdmNumber.errorCard)
            admNumberError = false
        }
        enableSignupButton()
    }

    private fun enableSignupButton() {
        binding.btSignup.isEnabled = true
    }

    private fun disableSignupButton() {
        binding.btSignup.isEnabled = false
    }

    private fun setupEditTexts() {

        binding.etName.apply {
            input.apply {
                inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                hint = getString(R.string.hint_name)
                doOnTextChanged { _, _, _, _ -> clearErrors() }
            }
            errorMessage.apply {
                text = getString(R.string.error_name)
            }
        }
        binding.etEmail.apply {
            input.apply {
                inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                hint = getString(R.string.hint_email)
                doOnTextChanged { _, _, _, _ -> clearErrors() }
            }
            errorMessage.apply {
                text = getString(R.string.error_email)
            }
        }
        binding.etPhone.apply {
            input.apply {
                inputType = InputType.TYPE_CLASS_PHONE
                filters = arrayOf<InputFilter>(LengthFilter(10))
                hint = getString(R.string.hint_phone)
                doOnTextChanged { _, _, _, _ -> clearErrors() }
            }
            errorMessage.apply {
                text = getString(R.string.error_phone)
            }
        }
        binding.etAdmNumber.apply {
            input.apply {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = getString(R.string.hint_adm_number)
                doOnTextChanged { _, _, _, _ -> clearErrors() }
            }
            errorMessage.apply {
                text = getString(R.string.error_adm_number)
            }
        }

    }

    private fun setupAvatars() {

        binding.avatar1.apply {
            avatarBindingList.add(this)
            avatarConstraint.setOnClickListener { selectAvatar(1) }
            avatar.setImageResource(R.drawable.avatar1)
            avatarBackground.setCardBackgroundColor(Color.WHITE)
        }
        binding.avatar2.apply {
            avatarBindingList.add(this)
            avatarConstraint.setOnClickListener { selectAvatar(2) }
            avatar.setImageResource(R.drawable.avatar2)
        }
        binding.avatar3.apply {
            avatarBindingList.add(this)
            avatarConstraint.setOnClickListener { selectAvatar(3) }
            avatar.setImageResource(R.drawable.avatar3)
        }
        binding.avatar4.apply {
            avatarBindingList.add(this)
            avatarConstraint.setOnClickListener { selectAvatar(4) }
            avatar.setImageResource(R.drawable.avatar4)
        }
        binding.avatar5.apply {
            avatarBindingList.add(this)
            avatarConstraint.setOnClickListener { selectAvatar(5) }
            avatar.setImageResource(R.drawable.avatar5)
        }
        binding.avatar6.apply {
            avatarBindingList.add(this)
            avatarConstraint.setOnClickListener { selectAvatar(6) }
            avatar.setImageResource(R.drawable.avatar6)
        }

    }

    private fun selectAvatar(avatarPosition: Int) {
        if (avatarPosition == selectedAvatarPosition) return
        avatarBindingList[selectedAvatarPosition - 1].avatarBackground.setCardBackgroundColor(Color.BLACK)
        animateOnClick(avatarBindingList[avatarPosition - 1].avatarConstraint)
        avatarBindingList[avatarPosition - 1].avatarBackground.setCardBackgroundColor(Color.WHITE)
        selectedAvatarPosition = avatarPosition
    }

    private fun initiatePayment(result: PaymentResponse) {

        val activity: Activity = requireActivity()
        val name = "Zealicon 2K22"
        val description = "you are just way far to enjoy your comics"
        var email = binding.etEmail.input.text.toString()
        var contact = binding.etPhone.input.text.toString()
        val key = result.key_id!!
        val orderId = result.order_id!!

        val checkout = Checkout()
        checkout.setKeyID(key)
        checkout.setImage(R.drawable.logo)

        val amount = 300
        val finalAmount = (amount.toFloat() * 100).toString()

        try {
            val options = JSONObject()
            options.put("name", name)
            options.put("description", description)
            options.put("theme.color", resources.getColor(R.color.red, null))
            options.put("order_id", orderId)
            options.put("currency", "INR")
            options.put("amount", finalAmount)
            val prefill = JSONObject()
            prefill.put("email", email)
            prefill.put("contact", contact)

            options.put("prefill", prefill)
            checkout.open(activity, options)
        } catch (e: Exception) {
            Timber.e(e)
        }

    }

    fun getReceiptData(): PaymentReceipt {
        return PaymentReceipt(
            "",
            "",
            "",
            paymentResponse.server_order_id!!,
            binding.etAdmNumber.input.text.toString(),
            "JSSATE",
            binding.etPhone.input.text.toString(),
            binding.etName.input.text.toString(),
            binding.etEmail.input.text.toString()
        )
    }
}
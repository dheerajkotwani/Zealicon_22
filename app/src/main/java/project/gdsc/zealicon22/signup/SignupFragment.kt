package project.gdsc.zealicon22.signup

import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentSignupBinding
import project.gdsc.zealicon22.databinding.ItemAvatarBinding
import project.gdsc.zealicon22.utils.animateOnClick
import project.gdsc.zealicon22.utils.animateToRemoveErrorMessage
import project.gdsc.zealicon22.utils.animateToShowErrorMessage


class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!


    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var selectedAvatarPosition = 1

    private var avatarBindingList: ArrayList<ItemAvatarBinding> = ArrayList()

    private var nameError: Boolean = false
    private var emailError: Boolean = false
    private var phoneError: Boolean = false
    private var admNumberError: Boolean = false

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
        setupEditTexts()
        setupAvatars()
    }

    private fun setupButton() {

        binding.btSignup.apply {
            setOnClickListener {
                animateOnClick(it)
                signup()
            }
        }

    }

    private fun signup() {

        if (haveInputErrors()) return

        // TODO handle signup case

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
        if (!email.matches(emailPattern.toRegex())){
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

}
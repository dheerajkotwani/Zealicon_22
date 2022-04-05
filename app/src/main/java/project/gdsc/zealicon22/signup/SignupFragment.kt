package project.gdsc.zealicon22.signup

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.FragmentSignupBinding
import project.gdsc.zealicon22.databinding.ItemAvatarBinding


class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null

    private val binding get() = _binding!!


    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var selectedAvatarPosition = 1

    private var avatarBindingList: ArrayList<ItemAvatarBinding> = ArrayList()

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



    }

    private fun setupEditTexts() {

        binding.etName.apply {
            input.apply {
                inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                hint = getString(R.string.hint_name)
            }
        }
        binding.etEmail.apply {
            input.apply {
                inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                hint = getString(R.string.hint_email)
            }
        }
        binding.etPhone.apply {
            input.apply {
                inputType = InputType.TYPE_CLASS_PHONE
                filters = arrayOf<InputFilter>(LengthFilter(10))
                hint = getString(R.string.hint_phone)
            }
        }
        binding.etAdmNumber.apply {
            input.apply {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = getString(R.string.hint_adm_number)
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
        avatarBindingList[avatarPosition - 1].avatarBackground.setCardBackgroundColor(Color.WHITE)
        selectedAvatarPosition = avatarPosition
    }

}
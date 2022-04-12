package project.gdsc.zealicon22.signup

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import project.gdsc.zealicon22.RegisterViewModel
import project.gdsc.zealicon22.databinding.DialogFindIdBinding

/**
 * Created by Nakul
 * on 12,April,2022
 */
class FindZealIdDialog(context: Context, val viewModel: RegisterViewModel): Dialog(context) {

    lateinit var binding: DialogFindIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogFindIdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        setUI()
    }

    private fun setUI() {
        binding.findButton.setOnClickListener {
            if (binding.input.text.isNullOrBlank()){
                Toast.makeText(context, "Enter something to proceed", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.findZealId(binding.input.text.toString())
        }
    }
}
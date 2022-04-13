package project.gdsc.zealicon22.signup

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import com.google.gson.Gson
import project.gdsc.zealicon22.RegisterViewModel
import project.gdsc.zealicon22.databinding.DialogFindIdBinding
import project.gdsc.zealicon22.di.AppModule
import project.gdsc.zealicon22.models.ResultHandler
import timber.log.Timber

/**
 * Created by Nakul
 * on 12,April,2022
 */
class FindZealIdDialog(context: Context,
                       val viewModel: RegisterViewModel,
                       val viewLifecycleOwner: LifecycleOwner
                       ): Dialog(context) {

    lateinit var binding: DialogFindIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogFindIdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        setListener()
        setUI()
    }


    private fun setListener() {

//        viewModel.

        viewModel.submitReceipt.observe(viewLifecycleOwner){

            when (it) {
                is ResultHandler.Loading -> {
                    Timber.d("RequestingRightNow")
                }
                is ResultHandler.Success -> {
                    Timber.d("SuccessRequest: ${it.result}")
                    val sp = AppModule.provideSharedPreferences(context)

                    if (it.result.zeal_id != null) {
                        sp.edit()
                            .putString("USER_DATA", Gson().toJson(it.result))
                            .putString("ZEAL_ID", it.result.zeal_id)
                            .apply()

                        Timber.d("SuccessRequest SharedPref: ${sp.getString("USER_DATA", "")}")
                        dismiss()

                    }
                    else {
                        sp.edit().remove("USER_DATA").remove("ZEAL_ID").apply()
                        Toast.makeText(
                            context,
                            "Registration data not found for user.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    //open ZealId screen after successful registration
                }
                is ResultHandler.Failure -> {
                    Timber.e("FailureRequest: ${it.message}")
                    Toast.makeText(
                        context,
                        "Registration data not found for user.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setUI() {
        binding.findButton.setOnClickListener {
            if (binding.input.text.isNullOrBlank()) {
                Toast.makeText(context, "Enter something to proceed", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.findZealId(binding.input.text.toString().toUpperCase())
        }
    }
}
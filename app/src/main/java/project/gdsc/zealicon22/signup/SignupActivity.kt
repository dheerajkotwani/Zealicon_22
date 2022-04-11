package project.gdsc.zealicon22.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import project.gdsc.zealicon22.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(binding.signupFrame.id, SignupFragment()).commit()

    }
}
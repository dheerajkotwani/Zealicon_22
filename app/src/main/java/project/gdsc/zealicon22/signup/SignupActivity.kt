package project.gdsc.zealicon22.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import project.gdsc.zealicon22.R
import project.gdsc.zealicon22.databinding.ActivityMainBinding
import project.gdsc.zealicon22.databinding.ActivitySignupBinding
import project.gdsc.zealicon22.home.HomeFragment

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(binding.mainFrame.id, SignupFragment()).commit()

    }
}
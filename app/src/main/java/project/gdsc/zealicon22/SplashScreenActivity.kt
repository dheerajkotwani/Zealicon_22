package project.gdsc.zealicon22

import project.gdsc.zealicon22.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import project.gdsc.zealicon22.databinding.ActivitySplashScreenBinding


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fadeIn: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.anim_fade_in)
        fadeIn.duration = 2000
        fadeIn.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                // do nothing
            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(p0: Animation?) {
                // do nothing
            }
        })
        binding.appLogo.startAnimation(fadeIn)

    }

}
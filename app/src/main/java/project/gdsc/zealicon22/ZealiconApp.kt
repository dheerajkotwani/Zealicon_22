package project.gdsc.zealicon22

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author Dheeraj Kotwani on 02/04/22.
 */
@HiltAndroidApp
class ZealiconApp : Application() {

    override fun onCreate() {
        super.onCreate()

//        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(applicationContext)

    }

}
package project.gdsc.zealicon22.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import project.gdsc.zealicon22.R

/**
 * Created by Nakul
 * on 09,April,2022
 */
abstract class Loading(context: Context) : Dialog(context) {

    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv = TextView(context).apply {
            text = "FETCHING COMICS..."
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundResource(android.R.color.transparent)
            typeface = ResourcesCompat.getFont(context, R.font.giants)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
            setTextColor(resources.getColor(R.color.white, null))
            gravity = Gravity.CENTER
        }
        setContentView(tv)
        this.setCancelable(false)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    abstract val backAction: () -> Unit

    override fun onBackPressed() {
        backAction()
    }

    fun dismissWithAnim() {
        val anim =
            { tv.animate().alpha(0f).setDuration(300L).withEndAction { super.dismiss() }.start() }
        Handler(Looper.getMainLooper()).postDelayed(anim, 1000L)
    }

}
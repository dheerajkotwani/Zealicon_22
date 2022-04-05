package project.gdsc.zealicon22.utils

import android.view.View

fun animateOnClick(view: View) {
    view.animate().apply {
        duration = 75
        scaleY(0.9f)
        scaleX(0.9f)
    }.withEndAction {
        view.animate().apply {
            duration = 25
            scaleY(1f)
            scaleX(1f)
        }
    }
}

fun animateToShowErrorMessage(view: View) {

    view.animate().apply {
        duration = 100
        yBy(50f)
    }

}

fun animateToRemoveErrorMessage(view: View) {

    view.animate().apply {
        duration = 100
        yBy(-50f)
    }

}
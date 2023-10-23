package com.bzzbzzbee.kcetestwork.utils.transitions

import android.graphics.Color
import androidx.annotation.IdRes
import androidx.transition.Transition
import com.bzzbzzbee.kcetestwork.R
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

sealed class TransitionAnimation(
    val enterAnim: Transition? = null,
    val reenterAnim: Transition? = null,
    val exitAnim: Transition? = null,
    val returnAnim: Transition? = null
) {
    class TransitionAnimationContainerTransform(
        @IdRes val target: Int,
        val sharedElementEnterTrans: Transition = sharedElementEnterTransition
    ): TransitionAnimation(
        enterAnim = MaterialFadeThrough(),
        returnAnim = MaterialFadeThrough()
    ) {
        companion object TransitionAnimationConstants {
            val sharedElementEnterTransition = MaterialContainerTransform().apply {
                drawingViewId = R.id.nav_host_fragment
                scrimColor = Color.TRANSPARENT
            }
        }
    }

    class TransitionAnimationSharedAxisZ : TransitionAnimation(
        enterAnim = MaterialSharedAxis(MaterialSharedAxis.Z, true),
        returnAnim = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    )
}
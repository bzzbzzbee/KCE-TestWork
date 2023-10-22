package com.bzzbzzbee.kcetestwork.presentation.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.bzzbzzbee.kcetestwork.MainActivity
import com.bzzbzzbee.kcetestwork.utils.transitions.TransitionAnimation

abstract class BaseFragment(@LayoutRes res: Int) : Fragment(res) {
    private val mainActivity: MainActivity
        get() = requireActivity() as MainActivity

    protected fun applyTransitionAnimation(animation: TransitionAnimation) {
        when (animation) {
            is TransitionAnimation.TransitionAnimationContainerTransform -> {
                enterTransition = animation.enterAnim?.apply {
                    removeTarget(animation.target)
                }
                reenterTransition = animation.reenterAnim
                sharedElementEnterTransition = animation.sharedElementEnterTrans
            }

            is TransitionAnimation.TransitionAnimationSharedAxisZ -> {
                enterTransition = animation.enterAnim
                returnTransition = animation.returnAnim
                exitTransition = animation.exitAnim
                reenterTransition = animation.reenterAnim
            }
        }
    }

    protected fun toggleCircleIndicator(isVisible: Boolean) {
        mainActivity.toggleCircleIndicator(isVisible)
    }
}
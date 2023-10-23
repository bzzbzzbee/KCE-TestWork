package com.bzzbzzbee.kcetestwork.presentation.cars

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bzzbzzbee.kcetestwork.R
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileMimeType
import com.bzzbzzbee.kcetestwork.databinding.FragmentCarImageFullscreenBinding
import com.bzzbzzbee.kcetestwork.domain.entities.CarFile
import com.bzzbzzbee.kcetestwork.presentation.base.BaseFragment
import com.bzzbzzbee.kcetestwork.utils.PicassoUtil.loadImageNoPlaceHolder
import com.bzzbzzbee.kcetestwork.utils.transitions.TransitionAnimation.TransitionAnimationContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarImageFullScreenFragment : BaseFragment(R.layout.fragment_car_image_fullscreen) {
    private var _binding: FragmentCarImageFullscreenBinding? = null
    private val binding
        get() = _binding!!

    private val args: CarImageFullScreenFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTransitionAnimation(
            TransitionAnimationContainerTransform(R.id.fullScreenImageView)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarImageFullscreenBinding.inflate(inflater, container, false)
        handleCarFile(args.carFile)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleCarFile(carFile: CarFile) {
        when (carFile.mimeType) {
            CarFileMimeType.PHOTO -> {
                if (!carFile.isLocal) binding.fullScreenImageView.loadImageNoPlaceHolder(carFile.resource)
                else {
                    val uri = Uri.parse(carFile.resource)
                    binding.fullScreenImageView.loadImageNoPlaceHolder(uri)
                }
            }
        }
    }
}

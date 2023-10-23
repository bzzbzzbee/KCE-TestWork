package com.bzzbzzbee.kcetestwork.presentation.cars

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bzzbzzbee.kcetestwork.R
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileMimeType
import com.bzzbzzbee.kcetestwork.databinding.FragmentCarDetailBinding
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.entities.CarFile
import com.bzzbzzbee.kcetestwork.presentation.base.BaseFragment
import com.bzzbzzbee.kcetestwork.utils.PicassoUtil.loadImage
import com.bzzbzzbee.kcetestwork.utils.addTextWatcher
import com.bzzbzzbee.kcetestwork.utils.collectLatestLifecycleFlow
import com.bzzbzzbee.kcetestwork.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailFragment : BaseFragment(R.layout.fragment_car_detail) {
    private var _binding: FragmentCarDetailBinding? = null
    private val binding
        get() = _binding!!

    private val carsViewModel: CarsViewModel by viewModels()
    private val carDetailViewModel: CarDetailViewModel by viewModels()

    private val args: CarDetailFragmentArgs by navArgs()

    private var lastUri = ""
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.carImageView.clear()
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                requireContext().contentResolver.takePersistableUriPermission(uri, flag)
                carDetailViewModel.postUiAction(
                    UiAction.ChangeFile(
                        CarFile(
                            id = args.car?.files?.firstOrNull()?.id ?: 0,
                            isLocal = true,
                            mimeType = CarFileMimeType.PHOTO,
                            resource = uri.toString()
                        )
                    )
                )
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        initViews()
        initObservers()
        initListeners()
        return binding.root
    }

    private fun initViews() {
        args.car?.let {
            carDetailViewModel.updateCar(it)
            binding.deleteCarButton.visible()
        }

        with(binding) {
            carColorLayout.addTextWatcher {
                carDetailViewModel.postUiAction(UiAction.ChangeColor(it))
            }
            carNameLayout.addTextWatcher {
                carDetailViewModel.postUiAction(UiAction.ChangeName(it))
            }
            rightHandedSwitcher.setOnCheckedChangeListener { _, isChecked ->
                carDetailViewModel.postUiAction(UiAction.ChangeRightHanded(isChecked))
            }
        }
    }


    private fun initObservers() {
        collectLatestLifecycleFlow(carDetailViewModel.carSF) {
            handleCar(it)
        }
        collectLatestLifecycleFlow(carDetailViewModel.isValidSF) {
            binding.saveCarButton.isEnabled = it
        }
    }

    private fun handleCar(car: Car) {
        with(binding) {
            rightHandedSwitcher.isChecked = car.isRightHanded


            carNameEditText.apply {
                setText(car.name)
                setSelection(length())
            }


            carColorEditText.apply {
                text?.clear()
                append(car.color)
                setSelection(length())
            }

            carColorLayout.setEndIconTintList(parseColor(car.color))

            handleCarFiles(car.files)
        }

    }

    private fun parseColor(hex: String): ColorStateList {
        val validColor = carDetailViewModel.checkHexColor(hex) != null
        val colorSL = Color.parseColor(
            if (validColor) hex else TRANSPARENT
        )
        return ColorStateList.valueOf(colorSL)
    }

    private fun handleCarFiles(carFiles: List<CarFile>) {
        if (carFiles.isNotEmpty()) {
            val firstFile = carFiles.first()

            when (firstFile.mimeType) {
                CarFileMimeType.PHOTO -> {
                    binding.carImageView.clear()
                    if (!firstFile.isLocal) {
                        binding.carImageView.loadImage(firstFile.resource)
                    } else {
                        val uri = Uri.parse(firstFile.resource)
                        if (uri.toString() != lastUri) {
                            binding.carImageView.loadImage(uri)
                            lastUri = uri.toString()
                        }
                    }
                }
            }
        } else handleNoFilesCar()
    }

    private fun handleNoFilesCar() {
        binding.carImageView.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_car,
                requireActivity().theme
            )
        )
        binding.carImageView.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.shape_circle,
            requireActivity().theme
        )
        binding.carImageView.backgroundTintList = ResourcesCompat.getColorStateList(
            resources,
            R.color.accent,
            requireActivity().theme
        )
    }

    private fun ImageView.clear() {
        background = null
        backgroundTintList = null
    }

    private fun initListeners() {
        with(binding) {
            carImageView.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            saveCarButton.setOnClickListener {
                val car = carDetailViewModel.carSF.value
                if (args.car == null) carsViewModel.addCar(car)
                else carsViewModel.editCar(car)
                findNavController().navigateUp()
            }

            deleteCarButton.setOnClickListener {
                carsViewModel.deleteCar(args.car!!)
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TRANSPARENT = "#00000000"
    }
}

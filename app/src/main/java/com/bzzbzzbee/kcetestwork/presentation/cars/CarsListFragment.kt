package com.bzzbzzbee.kcetestwork.presentation.cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.withCreated
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bzzbzzbee.kcetestwork.R
import com.bzzbzzbee.kcetestwork.databinding.FragmentCarListBinding
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.entities.CarFile
import com.bzzbzzbee.kcetestwork.presentation.base.BaseFragment
import com.bzzbzzbee.kcetestwork.presentation.base.UiState
import com.bzzbzzbee.kcetestwork.presentation.cars.adapter.CarAdapter
import com.bzzbzzbee.kcetestwork.utils.LinkCreator
import com.bzzbzzbee.kcetestwork.utils.RecyclerUtils.attachSwipeToAction
import com.bzzbzzbee.kcetestwork.utils.collectLatestLifecycleFlow
import com.bzzbzzbee.kcetestwork.utils.gone
import com.bzzbzzbee.kcetestwork.utils.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CarsListFragment : BaseFragment(R.layout.fragment_car_list) {
    private var _binding: FragmentCarListBinding? = null
    private val binding
        get() = _binding!!

    private val carsViewModel: CarsViewModel by activityViewModels()
    private val carAdapter = CarAdapter(::onCarIconClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarListBinding.inflate(inflater, container, false)
        initObservers()
        initListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        enableSwipeToEdit(carAdapter)
        binding.carsRecycler.adapter = carAdapter

        val fullText = LinkCreator.createLink(
            getString(R.string.no_cars_text),
            getString(R.string.add_car_text) to {
                //to new car
            }
        )
        binding.noCarsText.apply {
            text = fullText
            setLinkTextColor(
                resources.getColor(R.color.accent, requireActivity().theme)
            )
        }
    }

    private fun initObservers() {
        collectLatestLifecycleFlow(carsViewModel.carsSF) { uiState ->
            when (uiState) {
                is UiState.Error -> toggleCircleIndicator(false)
                is UiState.Idle -> {}
                is UiState.Loading -> toggleCircleIndicator(true)
                is UiState.Success -> {
                    handleCarList(uiState.data)
                }
            }
        }
    }

    private fun handleCarList(cars: List<Car>) {
        toggleCircleIndicator(false)
        if (cars.isEmpty()) {
            applyNoCarsMode()
        } else {
            applyCarsListMode(cars)
        }
    }

    private fun applyCarsListMode(cars: List<Car>) {
        with(binding) {
            carsRecycler.visible()
            filterCarButton.visible()
            noCarsText.gone()
        }
        carAdapter.submitList(cars.sortedBy { it.name })
    }

    private fun applyNoCarsMode() {
        with(binding) {
            carsRecycler.gone()
            filterCarButton.gone()
            noCarsText.visible()
        }
    }

    private fun initListeners() {
        with(binding) {
            addCarButton.setOnClickListener {

            }

            filterCarButton.setOnClickListener {
                val destination =
                    CarsListFragmentDirections.actionCarsListFragmentToMyDialogFragment()
                findNavController().navigate(destination)
            }
        }
    }

    private fun onCarIconClick(imageView: ImageView, carFile: CarFile) {
        val direction =
            CarsListFragmentDirections.actionCarsListFragmentToCarImageFullScreenFragment(carFile)

        val extras = FragmentNavigatorExtras(
            imageView to getString(R.string.item_icon_car_transition_name)
        )
        findNavController().navigate(direction, extras)
    }

    private fun enableSwipeToEdit(adapter: CarAdapter) {
        attachSwipeToAction(requireContext(), binding.carsRecycler) { position ->
            val item: Car = adapter.currentList[position]
        }
    }
}

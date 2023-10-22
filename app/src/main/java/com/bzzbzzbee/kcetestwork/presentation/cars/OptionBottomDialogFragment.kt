package com.bzzbzzbee.kcetestwork.presentation.cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.bzzbzzbee.kcetestwork.R
import com.bzzbzzbee.kcetestwork.databinding.DialogFilterRighthandedCarBinding
import com.bzzbzzbee.kcetestwork.presentation.cars.adapter.OptionAdapter
import com.bzzbzzbee.kcetestwork.presentation.models.Option
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OptionBottomDialogFragment : BottomSheetDialogFragment() {
    private var _binding: DialogFilterRighthandedCarBinding? = null
    private val binding: DialogFilterRighthandedCarBinding
        get() = _binding!!

    private val carsViewModel: CarsViewModel by activityViewModels()
    private val optionAdapter by lazy {
        OptionAdapter {
            carsViewModel.filterCars(it)
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFilterRighthandedCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.titleTextView.text = getText(R.string.select_type)
        val options = listOf(
            Option.All,
            Option.Right,
            Option.Left
        )
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.optionsRecycler.apply {
            addItemDecoration(itemDecorator)
            this.adapter = optionAdapter
        }
        optionAdapter.submitList(options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
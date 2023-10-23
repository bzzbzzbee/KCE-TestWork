package com.bzzbzzbee.kcetestwork.presentation.cars.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bzzbzzbee.kcetestwork.databinding.ItemOptionBinding
import com.bzzbzzbee.kcetestwork.presentation.models.Option

class OptionViewHolder(
    private val binding: ItemOptionBinding,
    private val onClick: (Option) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(option: Option) {
        val context = binding.root.context
        binding.optionTextView.apply {
            text = context.getString(option.textRes)
            setOnClickListener { onClick(option) }
        }

    }
}
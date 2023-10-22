package com.bzzbzzbee.kcetestwork.presentation.cars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bzzbzzbee.kcetestwork.databinding.ItemOptionBinding
import com.bzzbzzbee.kcetestwork.presentation.models.Option

class OptionAdapter(private val onClick: (Option) -> Unit) :
    ListAdapter<Option, OptionViewHolder>(OptionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val binding = ItemOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OptionViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package com.bzzbzzbee.kcetestwork.presentation.cars.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bzzbzzbee.kcetestwork.R
import com.bzzbzzbee.kcetestwork.data.room.models.CarFileMimeType
import com.bzzbzzbee.kcetestwork.databinding.ItemCarBinding
import com.bzzbzzbee.kcetestwork.domain.entities.Car
import com.bzzbzzbee.kcetestwork.domain.entities.CarFile
import com.bzzbzzbee.kcetestwork.utils.PicassoUtil.loadImage

class CarViewHolder(
    private val binding: ItemCarBinding,
    private val onClickIcon: (ImageView, CarFile) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(car: Car) {
        val context = binding.root.context
        with(binding) {
            carNameTextView.text = car.name
            carDesc.text =
                if (car.isRightHanded) context.getText(R.string.right_handed) else context.getText(R.string.left_handed)
            carColorImageView.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor(car.color))

            if (car.files.isNotEmpty()) {
                val firstFile = car.files.first()
                handleCarFile(firstFile, carIcon)

                carIcon.transitionName =
                    context.getString(
                        R.string.item_icon_car_transition_name,
                        layoutPosition.toString()
                    )

                carIcon.setOnClickListener {
                    onClickIcon(carIcon, firstFile)
                }
            } else applyDefaultIcon(context, carIcon)
        }
    }

    private fun handleCarFile(file: CarFile, imageView: ImageView) {
        when (file.mimeType) {
            CarFileMimeType.PHOTO -> {
                if (!file.isLocal) {
                    imageView.loadImage(file.resource)
                } else {
                    val uri = Uri.parse(file.resource)
                    imageView.loadImage(uri)
                }
            }
        }
    }

    private fun applyDefaultIcon(context: Context, imageView: ImageView) {
        imageView.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_car))
        imageView.background = AppCompatResources.getDrawable(context, R.drawable.shape_circle)
        imageView.backgroundTintList = AppCompatResources.getColorStateList(context, R.color.accent)
        imageView.setOnClickListener(null)
    }
}


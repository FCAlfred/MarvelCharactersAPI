package com.freddev.pokemonapi.extra

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.freddev.pokemonapi.R
import com.freddev.pokemonapi.databinding.CustomCharItemBinding

class CustomCharItem(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    private val binding: CustomCharItemBinding =
        CustomCharItemBinding.inflate(LayoutInflater.from(context), this)

    init {
        initComponents(context, attrs)
    }

    private fun initComponents(context: Context, attrs: AttributeSet?) {
        val attributes: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomCharItem)
        binding.customTitle.text = attributes.getString(R.styleable.CustomCharItem_title)
        binding.customDescription.text =
            attributes.getString(R.styleable.CustomCharItem_description)
        attributes.getString(R.styleable.CustomCharItem_imageUrl)?.let { setImage(it) }
        attributes.recycle()
    }

    fun setImage(url: String) {
        val width = 250 // Establecer el ancho deseado en píxeles
        val height = 250 // Establecer la altura deseada en píxeles

        Glide.with(context)
            .load(url)
            .override(width, height)
            .into(binding.customImage)
    }

    fun setTitleName(text: String) {
        binding.customTitle.text = text
    }

    fun getTitle(): String = binding.customTitle.text.toString()

    fun setDescription(text: String) {
        binding.customDescription.text = text
    }

    fun getSubTitle(): String = binding.customDescription.text.toString()
}
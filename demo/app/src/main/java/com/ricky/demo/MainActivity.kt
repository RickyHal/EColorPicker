package com.ricky.demo

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.ricky.color_picker.Util
import com.ricky.color_picker.api.OnAlphaSelectedListener
import com.ricky.color_picker.api.OnColorSelectedListener
import com.ricky.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.colorRingView.hasScaleMirror = true
        binding.colorPickerView.setOnColorSelectedListener(object : OnColorSelectedListener {
            override fun onColorSelecting(color: Int) {
                binding.ivColor.setBackgroundColor(color)
                binding.colorSlideView.setBaseColor(color)
                binding.alphaSlideView.setBaseColor(color)
                setColorText(color, (binding.ivColor.alpha * 255).toInt())
            }

            override fun onColorSelected(color: Int) {
                binding.ivColor.setBackgroundColor(color)
                binding.colorSlideView.setBaseColor(color)
                binding.alphaSlideView.setBaseColor(color)
                setColorText(color, (binding.ivColor.alpha * 255).toInt())
            }
        })
        binding.colorRingView.setSelectedBlockColor(Color.WHITE)
        binding.colorRingView.setOnColorSelectedListener(object : OnColorSelectedListener {
            override fun onColorSelecting(color: Int) {
                binding.ivColor.setBackgroundColor(color)
                binding.colorSlideView.setBaseColor(color)
                binding.alphaSlideView.setBaseColor(color)
                setColorText(color, (binding.ivColor.alpha * 255).toInt())
            }

            override fun onColorSelected(color: Int) {
                binding.ivColor.setBackgroundColor(color)
                binding.colorSlideView.setBaseColor(color)
                binding.alphaSlideView.setBaseColor(color)
                setColorText(color, (binding.ivColor.alpha * 255).toInt())
            }
        })
        binding.colorSlideView.setOnColorSelectedListener(object : OnColorSelectedListener {
            override fun onColorSelecting(color: Int) {
                binding.ivColor.setBackgroundColor(color)
                binding.alphaSlideView.setBaseColor(color)
                setColorText(color, (binding.ivColor.alpha * 255).toInt())
            }

            override fun onColorSelected(color: Int) {
                binding.ivColor.setBackgroundColor(color)
                binding.alphaSlideView.setBaseColor(color)
                setColorText(color, (binding.ivColor.alpha * 255).toInt())
            }
        })
        binding.alphaSlideView.setOnAlphaSelectedListener(object : OnAlphaSelectedListener {
            override fun onAlphaSelecting(alpha: Float) {
                binding.ivColor.alpha = alpha
                (binding.ivColor.background as? ColorDrawable)?.color?.let {
                    setColorText(it, (alpha * 255).toInt())
                }
            }

            override fun onAlphaSelected(alpha: Float) {
                binding.ivColor.alpha = alpha
                (binding.ivColor.background as? ColorDrawable)?.color?.let {
                    setColorText(it, (alpha * 255).toInt())
                }
            }
        })
    }


    @SuppressLint("SetTextI18n")
    private fun setColorText(@ColorInt color: Int, alpha: Int) {
        val text = Util.color2Hex(color, alpha)
        Log.e("111111", "color=$text   alpha=$alpha")
        binding.tvColor.text = "HEX: $text  A:$alpha  R:${color.red}  G:${color.green}  B:${color.blue}"
    }
}
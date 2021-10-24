package com.pancodedev.memorice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.pancodedev.memorice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setThemeIcon()

        binding.imgThemeSwitchIcon.setOnClickListener {
            switchTheme()
        }
    }

    /**
     * Sets the drawable used on Theme Switch Icon according to Light/Dark Mode
     */
    private fun setThemeIcon() {
        if(delegate.localNightMode == AppCompatDelegate.MODE_NIGHT_YES)
            binding.imgThemeSwitchIcon.setImageResource(R.drawable.ic_dark_mode_24)
        else
            binding.imgThemeSwitchIcon.setImageResource(R.drawable.ic_light_mode_24)
    }

    /**
     * Switches current Theme between Light/Dark Mode.
     */
    private fun switchTheme() {
        delegate.localNightMode =
            when(delegate.localNightMode) {
                AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_YES
            }
        setThemeIcon()
    }
}
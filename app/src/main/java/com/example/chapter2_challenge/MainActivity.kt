package com.example.chapter2_challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter2_challenge.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTipAmount.text = getString(R.string.tip_amount, 0.0)

        binding.btnCalculate.setOnClickListener {
            showTip()
        }
    }

    private fun calculateTip(tip: Int): Double {

        val tipAmount = when {
            binding.rbAmazing.isChecked -> tip * 0.20
            binding.rbGood.isChecked -> tip * 0.18
            else -> tip * 0.15
        }

        return tipAmount
    }

    private fun showTip() {
        val etService = binding.etService.text.toString()

        var tip = if (etService.isEmpty()) {
            0.0
        } else {
            calculateTip(etService.toInt())
        }

        if (binding.swRoundUp.isChecked) {
            tip = ceil(tip)
        }

        binding.tvTipAmount.text = getString(R.string.tip_amount, tip)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
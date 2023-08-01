package com.nnt.calculadoraimc_turmac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nnt.calculadoraimc_turmac.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            if(binding.editPeso.text.toString().isNotEmpty() && binding.editAltura.text.toString().isNotEmpty()) {
                val imc = calcular()
                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, digite seu peso e sua altura.", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun calcular(): Double {
        val peso = binding.editPeso.text.toString().replace(",", ".").toDouble()
        val altura = binding.editAltura.text.toString().replace(",", ".").toDouble()
        return peso / (altura * altura)
    }
}
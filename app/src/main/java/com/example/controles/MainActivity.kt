package com.example.controles

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.controles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    val CLAVE_CONTADOR = "clave_contador"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.textView2.text = "CONTADOR: " + viewModel.contador.toString()//nos sirve para mostrar un texto en pantalla

        binding.button2.setOnClickListener {
            //Toast.makeText(this@MainActivity, "Si", Toast.LENGTH_LONG).show()
            viewModel.contador+=1
            binding.textView2.text = "CONTADOR: " + viewModel.contador.toString()
        }

        viewModel.contador = savedInstanceState?.getInt(CLAVE_CONTADOR) ?: 0
        binding.textView2.text = "CONTADOR: " + viewModel.contador.toString()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(CLAVE_CONTADOR, viewModel.contador)
    }
}

class MainViewModel(private val estado: SavedStateHandle) : ViewModel() {
    private val CLAVE_CONTADOR = "clave_contador"

    var contador: Int
        get() = estado[CLAVE_CONTADOR] ?: 0
        set(value) {
            estado[CLAVE_CONTADOR] = value
        }
}

package com.mtthw.persontest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mtthw.persontest.data.ClientIntent
import com.mtthw.persontest.data.ClientViewState
import com.mtthw.persontest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ClientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                render(state)
            }
        }

        viewModel.handleIntent(ClientIntent.LoadClient)
    }

    private fun render(state: ClientViewState) {
        if (state.isLoading) {
            binding.clientName.text = "Loading..."
        } else if (state.error != null) {
            binding.clientName.text = "Error: ${state.error}"
        } else {
            binding.clientName.text = state.clientName
            binding.clientName.setTextColor(state.textColor)
        }
    }
}
package com.mtthw.persontest

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mtthw.persontest.data.ClientIntent
import com.mtthw.persontest.data.ClientViewState
import com.mtthw.persontest.databinding.ActivityMainBinding
import com.mtthw.persontest.location.GMSLocationService
import com.mtthw.persontest.location.HMSLocationService
import com.mtthw.persontest.location.LocationService
import com.mtthw.persontest.location.ServiceChecker
import kotlinx.coroutines.launch
import android.location.Location

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

        val locationService = getLocationService(this)
        locationService.getLastLocation { location ->
            updateLocation(location)
        }
    }

    private fun updateLocation(location: Location?) {
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            binding.coordinates.text = "Lat: $latitude, Lon: $longitude"
        } else {
            binding.coordinates.text = "Location not available"
        }
    }

    private fun getLocationService(context: Context): LocationService {
        return if (ServiceChecker.isGMSAvailable(context)) {
            GMSLocationService(context)
        } else if (ServiceChecker.isHMSAvailable(context)) {
            HMSLocationService(context)
        } else {
            throw UnsupportedOperationException("No supported location service available")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1001 || requestCode == 1002) {
            val locationService = getLocationService(this)
            locationService.getLastLocation { location ->
                updateLocation(location)
            }
        }
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
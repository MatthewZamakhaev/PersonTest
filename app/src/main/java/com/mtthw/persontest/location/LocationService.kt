package com.mtthw.persontest.location

import android.content.Context
import android.location.Location
interface LocationService {
    fun getLastLocation(onLocationReceived: (Location?) -> Unit)
}
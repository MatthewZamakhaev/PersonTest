package com.mtthw.persontest.location

import android.content.Context
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

object ServiceChecker {

    fun isGMSAvailable(context: Context): Boolean {
        val gmsAvailability = GoogleApiAvailability.getInstance()
        return gmsAvailability.isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS
    }

    fun isHMSAvailable(context: Context): Boolean {
        val hmsAvailability = HuaweiApiAvailability.getInstance()
        return hmsAvailability.isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS
    }
}
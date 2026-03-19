package com.vpvn.weatherapp.helper

import android.app.Activity
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

object LocationHelper {

    fun checkLocationSettings(
        activity: Activity,
        onLocationEnabled: () -> Unit,
        onResolutionRequired: (IntentSenderRequest) -> Unit
    ) {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            10_000L
        ).build()

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(activity)

        client.checkLocationSettings(builder.build())
            .addOnSuccessListener {
                onLocationEnabled()
            }
            .addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    val intentSenderRequest = IntentSenderRequest.Builder(
                        exception.resolution
                    ).build()

                    onResolutionRequired(intentSenderRequest)
                } else {
                    // GPS not resolvable
                }
            }
    }
}
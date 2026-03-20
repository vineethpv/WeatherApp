package com.vpvn.weatherapp.data.location

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.vpvn.weatherapp.domain.model.LocationCoordinates
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DefaultLocationProvider @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationProvider {

    @SuppressLint("MissingPermission")
    override suspend fun getLastLocation(): LocationCoordinates =
        suspendCancellableCoroutine { continuation ->

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (continuation.isActive) {
                        if (location != null) {
                            continuation.resume(
                                LocationCoordinates(
                                    latitude = location.latitude,
                                    longitude = location.longitude
                                )
                            )
                        } else {
                            continuation.resumeWithException(
                                IllegalStateException("Location is null")
                            )
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    if (continuation.isActive) {
                        continuation.resumeWithException(exception)
                    }
                }
        }

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): LocationCoordinates =
        suspendCancellableCoroutine { continuation ->

            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener { location ->

                if (continuation.isActive) {
                    if (location != null) {
                        continuation.resume(
                            LocationCoordinates(
                                latitude = location.latitude,
                                longitude = location.longitude
                            )
                        )
                    } else {
                        continuation.resumeWithException(
                            IllegalStateException("Location is null")
                        )
                    }
                }
            }.addOnFailureListener { exception ->
                if (continuation.isActive) {
                    continuation.resumeWithException(exception)
                }
            }

            continuation.invokeOnCancellation {
                cancellationTokenSource.cancel()
            }
        }
}
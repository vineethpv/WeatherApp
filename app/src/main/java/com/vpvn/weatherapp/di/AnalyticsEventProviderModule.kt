package com.vpvn.weatherapp.di

import com.vpvn.analyticssdk.api.EventProvider
import com.vpvn.weatherapp.analytics.AnalyticsTestEventProvider
import com.vpvn.weatherapp.analytics.AnalyticsWeatherEventProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsEventProviderModule {

    @Binds
    @IntoSet
    abstract fun bindAnalyticsProvider(provider: AnalyticsWeatherEventProvider): EventProvider

    @Binds
    @IntoSet
    abstract fun bindAnalyticsProvider(provider: AnalyticsTestEventProvider): EventProvider

}
package com.vpvn.analyticssdk.core.tracking

import com.vpvn.analyticssdk.core.event.Event
import kotlinx.coroutines.channels.Channel

class EventTracker(private val channel: Channel<TrackingCommand>) {

    fun track(event: Event) {
        channel.trySend(TrackingCommand.Persist(event))
    }
}
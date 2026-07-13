package com.vpvn.analyticssdk.core.tracking

import com.vpvn.analyticssdk.core.event.Event

sealed class TrackingCommand {
    data class Persist(val event: Event) : TrackingCommand()
}
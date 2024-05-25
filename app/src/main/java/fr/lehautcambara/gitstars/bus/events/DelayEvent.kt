package fr.lehautcambara.gitstars.bus.events

data class DelayEvent(val delayedEvent: BusEvent, val delay: Long) : BusEvent()

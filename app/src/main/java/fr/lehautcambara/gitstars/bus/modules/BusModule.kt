package fr.lehautcambara.gitstars.bus.modules

import fr.lehautcambara.gitstars.bus.Bus
import fr.lehautcambara.gitstars.bus.events.BusEvent

open class BusModule() {
    init {
        Bus.register(this)
    }

    fun post(event: BusEvent) {
        Bus.post(event)
    }

}
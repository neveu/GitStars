package fr.lehautcambara.gitstars.bus.events

import fr.lehautcambara.gitstars.bus.Bus
import java.io.Serializable
import java.util.Calendar

open class BusEvent() : Serializable {
    val timeStamp = Calendar.getInstance().timeInMillis
    fun before(event: BusEvent) = this.timeStamp < event.timeStamp
    fun after(event: BusEvent) = this.timeStamp > event.timeStamp

    fun post() {
        Bus.post(this)
    }
}
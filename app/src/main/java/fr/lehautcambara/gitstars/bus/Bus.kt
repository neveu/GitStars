package fr.lehautcambara.gitstars.bus

import fr.lehautcambara.gitstars.bus.events.BusEvent
import org.greenrobot.eventbus.EventBus

class Bus {
    companion object {
        fun post(event: BusEvent) {
            EventBus.getDefault().postSticky(event)
        }

        fun register(subscriber: Any) {
            EventBus.getDefault().register(subscriber)
        }

        fun unregister(subscriber: Any) {
            EventBus.getDefault().unregister(subscriber)
        }

        fun <T> getEvent (eventType: Class<T>): T? {
            return EventBus.getDefault().getStickyEvent(eventType)
        }

        fun reset() {
            EventBus.getDefault().removeAllStickyEvents()
        }
    }
}

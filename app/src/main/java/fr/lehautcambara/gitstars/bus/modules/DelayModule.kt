package fr.lehautcambara.gitstars.bus.modules

import fr.lehautcambara.gitstars.bus.Bus
import fr.lehautcambara.gitstars.bus.events.DelayEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.Timer
import kotlin.concurrent.schedule

class DelayModule: BusModule() {

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: DelayEvent) {
        Timer().schedule(event.delay) {
            Bus.post(event.delayedEvent)
        }
    }
}
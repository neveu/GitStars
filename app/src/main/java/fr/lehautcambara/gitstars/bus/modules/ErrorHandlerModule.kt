package fr.lehautcambara.gitstars.bus.modules

import fr.lehautcambara.gitstars.bus.events.GitHubServiceError
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ErrorHandlerModule : BusModule() {
   @Subscribe(threadMode = ThreadMode.BACKGROUND)
   fun onEvent(event: GitHubServiceError) {
       // handle the error here
   }
}
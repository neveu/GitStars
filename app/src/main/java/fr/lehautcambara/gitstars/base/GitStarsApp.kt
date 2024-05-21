package fr.lehautcambara.gitstars.base

import android.app.Application
import fr.lehautcambara.gitstars.bus.Bus
import fr.lehautcambara.gitstars.bus.events.RepoRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoResponseEvent
import fr.lehautcambara.gitstars.network.retrofit.GitServiceAPI
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class GitStarsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Bus.register(this)
        registerBusModules()
        RepoRequestEvent().post()
    }
    private fun registerBusModules() {
        GitServiceAPI()
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun onEvent(event: RepoResponseEvent) {
        val response = event.response
        val success = response.isSuccessful
    }
}
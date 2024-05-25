package fr.lehautcambara.gitstars.base

import android.app.Application
import fr.lehautcambara.gitstars.bus.modules.DelayModule
import fr.lehautcambara.gitstars.network.retrofit.GitServiceAPI

class GitStarsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerBusModules()
    }
    private fun registerBusModules() {
        DelayModule()
        GitServiceAPI()
    }

}
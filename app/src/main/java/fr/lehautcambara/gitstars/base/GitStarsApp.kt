package fr.lehautcambara.gitstars.base

import android.app.Application
import fr.lehautcambara.gitstars.bus.modules.DelayModule
import fr.lehautcambara.gitstars.bus.modules.ErrorHandlerModule
import fr.lehautcambara.gitstars.bus.modules.GitServiceAPI

class GitStarsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerBusModules()
    }
    private fun registerBusModules() {
        DelayModule()
        ErrorHandlerModule()
        GitServiceAPI()
    }

}
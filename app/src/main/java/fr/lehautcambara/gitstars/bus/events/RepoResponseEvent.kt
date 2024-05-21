package fr.lehautcambara.gitstars.bus.events

import fr.lehautcambara.gitstars.network.retrofit.RepoList
import retrofit2.Response

data class RepoResponseEvent(val event: RepoRequestEvent, val response: Response<RepoList>) : BusEvent() {

}

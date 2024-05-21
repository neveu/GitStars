package fr.lehautcambara.gitstars.network.retrofit

import android.util.Log
import fr.lehautcambara.gitstars.bus.Bus
import fr.lehautcambara.gitstars.bus.events.RepoContributorsRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoResponseEvent
import fr.lehautcambara.gitstars.bus.modules.BusModule
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Response

class GitServiceAPI : BusModule(){

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onEvent(event: RepoRequestEvent) {
        val response: Response<RepoList> = Core
            .retrofit()
            .create(GitHubService::class.java)
            .reposByStars()
            .execute()
        Bus.post(RepoResponseEvent(event, response))
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoResponseEvent) {
        with(event.response) {
            if (isSuccessful) {
                body()?.let { repoList ->
                    repoList.items.map { repo: Repo ->
                        with(repo) { post(RepoContributorsRequestEvent(name, owner.login))
                        }
                    }
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoContributorsRequestEvent) {
        val response = Core.retrofit().create(GitHubService::class.java).repoContributors(event.owner, event.name).execute()
        if (response.isSuccessful) {
            val contributors: List<Contributor>? = response.body()
            val topContrib = contributors?.first()
            Log.d("RepoContributorsRequestEvent", topContrib?.login?:"-- none --")
        } else {
            Log.d("RepoContributorsRequestEvent", "-- none --")
        }
    }
}



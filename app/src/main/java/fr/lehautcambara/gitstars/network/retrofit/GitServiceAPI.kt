package fr.lehautcambara.gitstars.network.retrofit

import android.util.Log
import fr.lehautcambara.gitstars.bus.events.RepoContributorsRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoResponseEvent
import fr.lehautcambara.gitstars.bus.events.RepoWithContributorsEvent
import fr.lehautcambara.gitstars.bus.events.RetrofitError
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
        if (response.isSuccessful) {
            post(RepoResponseEvent(event, response.body()))
        }
        else {
            post(RetrofitError(event, response))
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onEvent(event: RepoResponseEvent) {
        event.repoList?.apply{
            items.forEach{ repo: Repo ->
                with(repo) { post(RepoContributorsRequestEvent(this, name, owner.login)) }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onEvent(event: RepoContributorsRequestEvent) {
        val response = Core.retrofit().create(GitHubService::class.java).repoContributors(event.name, event.owner).execute()
        if (response.code() == 200) {
            val contribs: List<Contributor>? = response.body()
            val repo = event.repo
            post(RepoWithContributorsEvent(repo, contribs))
        } else {
            Log.d("RepoContributorsRequestEvent", "${event.repo.name} unsuccessful")
            post(RepoWithContributorsEvent(event.repo, null))

        }
    }
}



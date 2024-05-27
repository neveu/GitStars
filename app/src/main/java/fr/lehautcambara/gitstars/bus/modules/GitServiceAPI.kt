package fr.lehautcambara.gitstars.bus.modules

import fr.lehautcambara.gitstars.bus.events.GitHubServiceError
import fr.lehautcambara.gitstars.bus.events.RepoContributorsRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoResponseEvent
import fr.lehautcambara.gitstars.bus.events.RepoWithContributorsEvent
import fr.lehautcambara.gitstars.bus.events.RetrofitError
import fr.lehautcambara.gitstars.network.retrofit.Contributor
import fr.lehautcambara.gitstars.network.retrofit.Core
import fr.lehautcambara.gitstars.network.retrofit.GitHubService
import fr.lehautcambara.gitstars.network.retrofit.Repo
import fr.lehautcambara.gitstars.network.retrofit.RepoList
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitServiceAPI : BusModule(){
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
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

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoResponseEvent) {
        event.repoList?.apply{
            items.forEach{ repo: Repo ->
                with(repo) { post(RepoContributorsRequestEvent(this)) }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoContributorsRequestEvent) {
        Core.retrofit()
            .create(GitHubService::class.java)
            .repoContributors( event.repo.owner.login, event.repo.name,)
            .enqueue(object : Callback<List<Contributor>>{ // let Retrofit handle asynchrony
                override fun onResponse(p0: Call<List<Contributor>>, response: Response<List<Contributor>>) {
                    when(response.code()) {
                        200 -> post(RepoWithContributorsEvent(event.repo, response.body()))
                        203, 403 -> post(RepoWithContributorsEvent(event.repo, null))
                        else -> post(RepoWithContributorsEvent(event.repo, null))
                    }
                }

                override fun onFailure(p0: Call<List<Contributor>>, p1: Throwable) {
                    post(GitHubServiceError(event, p1))
                }
            })
    }

}



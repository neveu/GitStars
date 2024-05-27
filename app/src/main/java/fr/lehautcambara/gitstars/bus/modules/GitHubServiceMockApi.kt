package fr.lehautcambara.gitstars.bus.modules

import android.util.Log
import fr.lehautcambara.gitstars.bus.events.DelayEvent
import fr.lehautcambara.gitstars.bus.events.RepoContributorsRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoRequestEvent
import fr.lehautcambara.gitstars.bus.events.RepoResponseEvent
import fr.lehautcambara.gitstars.bus.events.RepoWithContributorsEvent
import fr.lehautcambara.gitstars.network.retrofit.Contributor
import fr.lehautcambara.gitstars.network.retrofit.Owner
import fr.lehautcambara.gitstars.network.retrofit.Repo
import fr.lehautcambara.gitstars.network.retrofit.RepoList
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.Random

class GitHubServiceMockApi : BusModule() {
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoRequestEvent) {
        post(
            RepoResponseEvent(event, RepoList(10, false, generateMockRepoList().toTypedArray()))
        )
    }
    private fun generateMockRepoList(n: Int = 10): MutableList<Repo> {
        val repos = mutableListOf<Repo>()
        for(i in 1..n) {
            val owner = Owner(i.toLong(), "owner$i", "avatar url here")
            val repo = Repo(i.toLong(), "foo$i", owner, "https://foo$i", random.nextInt(100).toLong())
            repos.add(repo)
        }
        return repos
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoResponseEvent) {
        event.repoList?.apply{
            items.forEach{ repo: Repo ->
                Log.d("debugmockrepo", repo.name)
                with(repo) { post(RepoContributorsRequestEvent(this)) }
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEvent(event: RepoContributorsRequestEvent) {
        val repo = event.repo
        val delay  = random.nextInt(1000).toLong()
        post(DelayEvent(RepoWithContributorsEvent(repo, mockContribList()), delay))

    }

    companion object {
        val random = Random()
        fun mockContribList(): List<Contributor> {
            val c1 = Contributor("c1",1,"", 10)
            val c2 = Contributor("c2",2,"", 9)
            val c3 = Contributor("c3",3,"", 11)
            return listOf(c1,c2,c3)
        }
    }

}
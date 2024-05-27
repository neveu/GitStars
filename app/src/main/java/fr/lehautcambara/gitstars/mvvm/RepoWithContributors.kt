package fr.lehautcambara.gitstars.mvvm

import fr.lehautcambara.gitstars.network.retrofit.Contributor
import fr.lehautcambara.gitstars.network.retrofit.Owner
import fr.lehautcambara.gitstars.network.retrofit.Repo
import kotlin.random.Random

// Compose the repo and the contributors into a single object
data class RepoWithContributors(val repo: Repo , val contribs: List<Contributor>?) {
    // companion object for generating mock objects for testing

    companion object {
        private fun makeRandom(i: Int) : RepoWithContributors {
            return RepoWithContributors(
                Repo(0, "name$i", owner = Owner(0, "owner$i", "avatar_url"), "contributors url", Random.nextLong(100, 1000)),
                contribs = listOf(
                    Contributor("C1", 0, "avatar_url", 1000),
                    Contributor("C2", 1, "avatar_url", 100),
                    Contributor("C3", 2, "avatar_url", 10),
                )
            )
        }
        fun makeRandomList(n: Int) : List<RepoWithContributors> {
           return (1..n).toList().map { i-> makeRandom(i) }
        }
    }
}

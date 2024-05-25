package fr.lehautcambara.gitstars.bus.events

import fr.lehautcambara.gitstars.network.retrofit.Contributor
import fr.lehautcambara.gitstars.network.retrofit.Repo
import fr.lehautcambara.gitstars.mvvm.RepoWithContributors

data class RepoWithContributorsEvent(val repo: Repo, val contribs: List<Contributor>?) : BusEvent() {
    val repoWithContributors: RepoWithContributors = RepoWithContributors(repo, contribs)
}


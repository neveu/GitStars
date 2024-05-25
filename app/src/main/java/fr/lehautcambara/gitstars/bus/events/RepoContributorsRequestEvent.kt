package fr.lehautcambara.gitstars.bus.events

import fr.lehautcambara.gitstars.network.retrofit.Repo

data class RepoContributorsRequestEvent(val repo: Repo, val owner: String, val name: String) : BusEvent()

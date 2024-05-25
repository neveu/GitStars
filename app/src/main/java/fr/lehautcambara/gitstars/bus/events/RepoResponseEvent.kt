package fr.lehautcambara.gitstars.bus.events

import fr.lehautcambara.gitstars.network.retrofit.RepoList

data class RepoResponseEvent(val event: RepoRequestEvent, val repoList: RepoList?) : BusEvent()

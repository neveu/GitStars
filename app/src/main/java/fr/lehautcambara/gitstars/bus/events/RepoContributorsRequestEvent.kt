package fr.lehautcambara.gitstars.bus.events

data class RepoContributorsRequestEvent(val owner: String, val name: String) : BusEvent() {

}

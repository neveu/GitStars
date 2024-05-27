package fr.lehautcambara.gitstars.bus.events

class GitHubServiceError(event: RepoContributorsRequestEvent, throwable: Throwable) : BusEvent()

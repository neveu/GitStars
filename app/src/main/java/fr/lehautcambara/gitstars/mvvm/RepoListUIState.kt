package fr.lehautcambara.gitstars.mvvm

data class RepoListUIState(
    val repoWithContribList: List<RepoWithContributors> = listOf(),
    val progress: Float? = null
    )



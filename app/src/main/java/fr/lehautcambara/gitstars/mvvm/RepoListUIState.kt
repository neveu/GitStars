package fr.lehautcambara.gitstars.mvvm

data class RepoListUIState(
    val repoWithContribList: List<RepoWithContributors> = listOf(),
    val loading: Boolean = false,
    val progress: Float? = null
    )



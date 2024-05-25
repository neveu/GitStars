package fr.lehautcambara.gitstars.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.lehautcambara.gitstars.mvvm.RepoListUIState
import fr.lehautcambara.gitstars.mvvm.RepoWithContributors
import fr.lehautcambara.gitstars.ui.theme.GitStarsTheme
import kotlinx.coroutines.flow.StateFlow

@Composable
fun RepositoriesScreen(uiState: StateFlow<RepoListUIState>, modifier: Modifier = Modifier) {
    RepositoryList(uiState)
}

@Composable
private fun RepositoryList(uiState: StateFlow<RepoListUIState>) {
    val repoListUIState: RepoListUIState by uiState.collectAsState()
    RepositoryList(repoListUIState)
}

@Composable
private fun RepositoryList(repoListUIState: RepoListUIState) {
    val repoList: List<RepoWithContributors> = repoListUIState
        .repoWithContribList
        .sortedByDescending{
            it.repo.stargazers_count
        }
    RepositoryList(repoList,  repoListUIState.progress)
}

@Composable
private fun RepositoryList(repoList: List<RepoWithContributors>, progress: Float? = 0.5F) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    )
    {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(repoList) { repo ->
                RepoBox(
                    repo.repo.name,
                    repo.contribs?.get(0)?.login ?: "Too many contribs to list",
                    repo.repo.stargazers_count
                )
            }
        }
        if (progress == null) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 5.dp,
                modifier = Modifier
                    .size(100.dp)
                    .align(Center),
            )
        } else if (progress < 0.9) {
            CircularProgressIndicator(
                progress = progress,
                color = Color.Red,
                strokeWidth = 5.dp,
                modifier = Modifier
                    .size(100.dp)
                    .align(Center),
            )
        } else {
            Log.d("RepositoryScreen", "not loading")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRepositoriesScreen() {
    GitStarsTheme {
        RepositoryList(RepoWithContributors.makeRandomList(50))
    }
}
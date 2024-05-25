package fr.lehautcambara.gitstars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.lehautcambara.gitstars.bus.events.RepoRequestEvent
import fr.lehautcambara.gitstars.mvvm.RepoListVm
import fr.lehautcambara.gitstars.ui.RepositoriesScreen
import fr.lehautcambara.gitstars.ui.theme.GitStarsTheme

class MainActivity : ComponentActivity() {
    private var repoListVm = RepoListVm()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitStarsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RepositoriesScreen(repoListVm.uiState)
                }
            }
        }
        RepoRequestEvent().post()
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewRepositoriesScreen() {
    GitStarsTheme {
        RepositoriesScreen(RepoListVm().uiState)
    }
}
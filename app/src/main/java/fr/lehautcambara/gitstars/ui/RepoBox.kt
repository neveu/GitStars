package fr.lehautcambara.gitstars.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.lehautcambara.gitstars.ui.theme.GitStarsTheme

@Composable
fun RepoBox(name: String, contribLogin: String, stars: Long = 999){
    Column(Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(Modifier
            .background(Color.White)
        ) {
            Text(name, color = Color.Black )
            Spacer(Modifier.width(20.dp))
            Text("Stars: $stars", color = Color.Red)
        }

        Text(contribLogin, color = Color.Black )
    }
}

@Preview
@Composable
fun PreviewRepoBox() {
    GitStarsTheme {
        RepoBox("Bobs Repo.c", "Some Guy Bob")
    }
}
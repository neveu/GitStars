package fr.lehautcambara.gitstars.network.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class GitServiceAPI {
    private interface GitHubService {
        @GET("search/repositories?q=stars:>0&sort=stars&per_page=100")
        fun reposByStars() : Call<List<Repo>>

        @GET("repos/{owner}/{repo}/contributors")
        fun repoContributors(
            @Path("owner") owner: String,
            @Path("repo") repo: String,
        ) : Call<List<Contributor>>
    }
}



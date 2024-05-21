package fr.lehautcambara.gitstars.network.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GitHubService {
    @Headers("Authorization:  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")
    @GET("search/repositories?q=stars:>0&sort=stars&per_page=100")
    fun reposByStars() : Call<RepoList>

    @Headers("Authorization:  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")
    @GET("repos/{owner}/{name}/contributors")
    fun repoContributors(
        @Path("owner") owner: String,
        @Path("name") name: String,
    ) : Call<List<Contributor>>
}

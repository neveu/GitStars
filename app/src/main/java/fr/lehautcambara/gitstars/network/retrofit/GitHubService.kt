package fr.lehautcambara.gitstars.network.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

//private const val Authorization = "Authorization: Bearer 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"
private const val Authorization = "Authorization: Bearer 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"

interface GitHubService {
    @Headers(Authorization)
    @GET("search/repositories?q=stars:>0&sort=stars&per_page=100")
    fun reposByStars() : Call<RepoList>

    @Headers(Authorization)
    @GET("repos/{name}/{owner}/contributors")
    fun repoContributors(
        @Path("name") name: String,
        @Path("owner") owner: String,
    ) : Call<List<Contributor>>
}

package fr.lehautcambara.gitstars.network.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val Authorization0 = "Authorization: Bearer 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"
private const val Authorization1 = "Authorization: Bearer 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"
private const val Authorization2 = "Authorization: Bearer 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"


interface GitHubService {
    @Headers(Authorization2)
    @GET("search/repositories?q=stars:>0&sort=stars&per_page=100")
    fun reposByStars() : Call<RepoList>

    @Headers(Authorization2)
    @GET("repos/{name}/{owner}/contributors")
    fun repoContributors(
        @Path("name") name: String,
        @Path("owner") owner: String,
    ) : Call<List<Contributor>>
}

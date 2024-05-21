package fr.lehautcambara.gitstars.network.retrofit

import com.squareup.moshi.Json

data class Repo(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "owner") val owner: Owner,
    @field:Json(name = "contributors_url") val contributors_url: String,
    @field:Json(name = "stargazers_count") val stargazers_count: Long,
)

data class Owner(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "login") val login: String,
    @field:Json(name = "avatar_url") val avatar_url: String,
    )

data class Contributor(
    @field:Json(name = "login") val login: String,
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "avatar_url") val avatar_url: String,
    @field:Json(name = "contributions") val contributions: Long,
    )

data class RepoList(
    @field:Json(name = "total_count") val total_count: Long,
    @field:Json(name = "incomplete_results") val incomplete_results: Boolean,
    @field:Json(name = "items") val items: Array<Repo>,
)

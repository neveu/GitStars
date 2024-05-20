package fr.lehautcambara.gitstars.network.retrofit

import com.squareup.moshi.Json

data class Repo(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "owner") val owner: Owner,
)

data class Owner(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "login") val login: String,
    )

data class Contributor(
    @field:Json(name = "login") val login: String,
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "contributions") val contributions: Long,

    )
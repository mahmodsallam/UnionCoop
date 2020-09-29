package com.task.task.data.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GithubReposRemoteDS {
    @GET("repositories")
     fun getGithubRepos():Deferred<List<GithubReposResponse>>
}
package com.task.task.data.repository

import com.task.task.data.remote.GithubReposRemoteDS
import com.task.task.data.remote.GithubReposResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class GithubDataRepository @Inject constructor(private val githubReposRemoteDS: GithubReposRemoteDS) :
    IGithubDataRepository {
    override fun getGithubRepos(): Deferred<List<GithubReposResponse>> {
        return githubReposRemoteDS.getGithubRepos()

    }
}
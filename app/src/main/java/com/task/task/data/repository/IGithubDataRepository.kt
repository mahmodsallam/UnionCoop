package com.task.task.data.repository

import com.task.task.data.remote.GithubReposResponse
import kotlinx.coroutines.Deferred

interface IGithubDataRepository {
    fun getGithubRepos(): Deferred<List<GithubReposResponse>>
}
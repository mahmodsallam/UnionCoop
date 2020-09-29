package com.task.task.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.task.data.remote.GithubReposResponse
import com.task.task.data.repository.GithubDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val githubDataRepository: GithubDataRepository) :
    ViewModel() {
    private var _githubReposList = MutableLiveData<List<GithubReposResponse>>()
    val githubReposList = _githubReposList
    private var _showErrorUI = MutableLiveData<Boolean>()
    val showErrorUI = _showErrorUI

    fun getGithubRepos() {
        viewModelScope.launch(Job() + Dispatchers.Main) {
            try {
                val githubReposResponse = githubDataRepository.getGithubRepos()
                githubReposResponse?.await()?.let {
                    _githubReposList.value = it
                    _showErrorUI.value = false
                }
            } catch (exception: Exception) {
                _showErrorUI.value = true
            }
        }
    }
}
package com.task.task.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.task.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.getGithubRepos()
        updateUI()
        swipeRefresh.setOnRefreshListener {
            mainViewModel.getGithubRepos()
            swipeRefresh.isRefreshing = true
            updateUI()

        }

        btnErrorRetry.setOnClickListener(this)

    }

    private fun updateUI() {
        mainViewModel.githubReposList.observe(this, Observer {
            swipeRefresh.isRefreshing = false
            rvGithubRepos.visibility = View.VISIBLE
            errorLayout.visibility = View.GONE
            rvGithubRepos.layoutManager = LinearLayoutManager(this)
            rvGithubRepos.adapter = GithubReposAdapter(this, it)
        })
        mainViewModel.showErrorUI.observe(this, Observer {
            swipeRefresh.isRefreshing = false
            if (it == true) {
                errorLayout.visibility = View.VISIBLE
                rvGithubRepos.visibility = View.GONE
            } else {
                rvGithubRepos.visibility = View.VISIBLE
                errorLayout.visibility = View.GONE
            }
        })
    }

    override fun onClick(p0: View?) {
        mainViewModel.getGithubRepos()
        updateUI()
    }
}
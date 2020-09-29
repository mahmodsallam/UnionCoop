package com.task.task.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.task.R
import com.task.task.data.remote.GithubReposResponse
import kotlinx.android.synthetic.main.item_github_repo.view.*
import kotlinx.android.synthetic.main.repo_dec_layout.view.*

class GithubReposAdapter(
    private val context: Context,
    private val githubReposList: List<GithubReposResponse>
) :
    RecyclerView.Adapter<GithubReposAdapter.GithubReposViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubReposViewHolder {
        return GithubReposViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_github_repo, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return githubReposList.size
    }

    override fun onBindViewHolder(holder: GithubReposViewHolder, position: Int) {
        with(holder.itemView) {
            tvAuthor.text = githubReposList[position].author
            tvName.text = githubReposList[position].name
            tvDescription.text = githubReposList[position].description
            tvStars.text = githubReposList[position].stars.toString()
            tvForks.text = githubReposList[position].forks.toString()
            tvLanguage.text = githubReposList[position].language
            Glide.with(context).load(githubReposList[position].avatar)
                .into(ivAvatar)
        }

        holder.itemView.setOnClickListener {
            with(holder.itemView) {
                if (!tvDescription.isVisible) {
                    tvDescription.visibility = View.VISIBLE
                    layoutRepoForkStars.visibility = View.VISIBLE

                } else {
                    tvDescription.visibility = View.GONE
                    layoutRepoForkStars.visibility = View.GONE
                }

            }

        }

    }

    class GithubReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
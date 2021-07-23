package com.example.application.presentation.ui.repository.repositoryDownloaded

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R
import com.example.application.databinding.DownloadedRepositoryBinding
import com.example.application.databinding.ItemRepositoryBinding
import com.example.application.domain.model.DownloadRepositoryModel
import com.example.application.domain.model.Repository
import com.example.application.presentation.ui.user.OnUserClickListener

class DownloadedRepositoryAdapter() :
    RecyclerView.Adapter<DownloadedRepositoryAdapter.RepositoryViewHolder>() {

    private var listData = ArrayList<DownloadRepositoryModel>()

    fun setData(newListData: List<DownloadRepositoryModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DownloadedRepositoryBinding.bind(itemView)
        fun bind(data: DownloadRepositoryModel) {
            with(binding) {
                tvName.text = data.owner.login
                repName.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder =
        RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.downloaded_repository, parent, false)
        )

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(listData[position])
    }
    override fun getItemCount() = listData.size
}
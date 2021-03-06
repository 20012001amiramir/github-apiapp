package com.example.application.presentation.ui.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R
import com.example.application.databinding.ItemRepositoryBinding
import com.example.application.domain.model.Repository
import com.example.application.presentation.ui.user.OnUserClickListener

class RepositoryAdapter(private val onRepositoryClickListener: OnRepositoryClickListener) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private var listData = ArrayList<Repository>()


    fun setData(newListData: List<Repository>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRepositoryBinding.bind(itemView)
        fun bind(data: Repository) {
            with(binding) {
                tvName.text = data.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder =
        RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        )

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(listData[position])
        holder.itemView.setOnClickListener {
            listData[position].owner.login?.let { it1 ->
                onRepositoryClickListener.onRepositoryClicked(
                    listData[position].name,
                    listData[position].url,
                    it1
                )
            }
        }
    }
    override fun getItemCount() = listData.size
}
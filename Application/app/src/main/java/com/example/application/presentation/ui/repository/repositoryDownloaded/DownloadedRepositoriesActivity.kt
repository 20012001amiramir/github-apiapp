package com.example.application.presentation.ui.repository.repositoryDownloaded

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.application.App
import com.example.application.R
import com.example.application.data.Resource
import com.example.application.databinding.ActivityRepositoriesBinding
import com.example.application.databinding.DownloadedRepositoriesBinding
import com.example.application.databinding.DownloadedRepositoryBinding
import com.example.application.presentation.ui.repository.repositoryEntity.RepositoryDownloadActivity
import com.example.application.presentation.viewmodel.DownloadedRepositoryViewModel
import com.example.application.presentation.viewmodel.RepositoryViewModel
import timber.log.Timber
import javax.inject.Inject


class DownloadedRepositoriesActivity : AppCompatActivity(){

    @Inject
    lateinit var factoryRepository: RepositoryDownloadViewModelFactory
    private val viewModel: DownloadedRepositoryViewModel by viewModels {
        factoryRepository
    }

    private lateinit var binding: DownloadedRepositoriesBinding
    private lateinit var repositoryAdapter: DownloadedRepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.injectDown(this)
        super.onCreate(savedInstanceState)
        setupBackButton()
        binding = DownloadedRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        repositoryAdapter = DownloadedRepositoryAdapter()
        setContentView(view)
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@DownloadedRepositoriesActivity)
            setHasFixedSize(true)
            adapter = repositoryAdapter
        }
        getDownloadedRepositories()
    }

    private fun setupBackButton() {
        (this as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getDownloadedRepositories() {
        viewModel.getRepositories().observe(this, { user ->
            if (user != null) {
                when (user) {
                    is Resource.Loading -> {
                        binding.rvUser.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.rvUser.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                        repositoryAdapter.setData(user.data)
                    }
                    is Resource.Error -> {
                        binding.rvUser.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                        binding.viewError.tvError.text =
                            user.message ?: getString(R.string.something_wrong)
                        user.message?.let { Log.e("erroramir", it) }
                    }
                }
            }
        })
    }

}
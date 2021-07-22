package com.example.application.presentation.ui.repository

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
import com.example.application.presentation.ui.repository.repositoryEntity.RepositoryDownloadActivity
import com.example.application.presentation.viewmodel.RepositoryViewModel
import timber.log.Timber
import javax.inject.Inject


class RepositoriesActivity : AppCompatActivity(), OnRepositoryClickListener{

    @Inject
    lateinit var factoryRepository: RepositoryViewModelFactory
    private val viewModel: RepositoryViewModel by viewModels {
        factoryRepository
    }

    private lateinit var binding: ActivityRepositoriesBinding
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.injectR(this)
        super.onCreate(savedInstanceState)
        setupBackButton()
        binding = ActivityRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        repositoryAdapter = RepositoryAdapter(this)
        setContentView(view)
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@RepositoriesActivity)
            setHasFixedSize(true)
            adapter = repositoryAdapter
        }
        val intent = intent
        val name = intent.getStringExtra("name")
        if (name != null) {
            getRepositories(name)
        }
        else Timber.e("Don't send name")
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

    private fun getRepositories(q: String) {
        viewModel.getRepositories(q).observe(this, { user ->
            Timber.e("${user.data}")
            if (user != null) {
                when (user) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.viewStarter.root.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.rvUser.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                        repositoryAdapter.setData(user.data)
                    }
                    is Resource.Error -> {
                        binding.rvUser.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.viewStarter.root.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            user.message ?: getString(R.string.something_wrong)
                        user.message?.let { Log.e("erroramir", it) }
                    }
                }
            }
        })
    }
    override fun onRepositoryClicked(name: String, url: String, ownerName: String) {
        val intent = Intent(this, RepositoryDownloadActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("url", url)
        intent.putExtra("ownerName", ownerName)
        startActivity(intent)
    }

}
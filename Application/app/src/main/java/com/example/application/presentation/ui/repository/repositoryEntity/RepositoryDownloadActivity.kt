package com.example.application.presentation.ui.repository.repositoryEntity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.application.App
import com.example.application.databinding.ActivityRepositoryDownloadBinding
import com.example.application.domain.model.DownloadRepositoryModel
import com.example.application.domain.model.OwnerModel
import com.example.application.presentation.ui.repository.RepositoryViewModelFactory
import com.example.application.presentation.ui.repository.repositoryDownloaded.RepositoryDownloadViewModelFactory
import com.example.application.presentation.viewmodel.DownloadedRepositoryViewModel
import com.example.application.presentation.viewmodel.RepositoryViewModel
import javax.inject.Inject


class RepositoryDownloadActivity : AppCompatActivity() {

    @Inject
    lateinit var factoryDownloadRepository: RepositoryDownloadViewModelFactory
    private val viewModel: DownloadedRepositoryViewModel by viewModels {
        factoryDownloadRepository
    }

    private var url : String = ""
    private lateinit var binding: ActivityRepositoryDownloadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.injectD(this)
        super.onCreate(savedInstanceState)
        setupBackButton()
        binding = ActivityRepositoryDownloadBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        val name = intent.getStringExtra("name")
        val urlX =  intent.getStringExtra("url")
        val ownerName = intent.getStringExtra("ownerName")
        url = "$urlX/archive/refs/heads/master.zip"
        binding.textV.text = name
        binding.browser.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(urlX)
            startActivity(i)
        }
        binding.download.setOnClickListener {
            viewModel.setData(DownloadRepositoryModel(name,url, OwnerModel(ownerName)))
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
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
}
package com.example.application.presentation.ui.user

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.application.App
import com.example.application.R
import com.example.application.data.Resource
import com.example.application.databinding.AppBarBinding
import com.example.application.domain.model.Repository
import com.example.application.presentation.ui.repository.RepositoriesActivity
import com.example.application.presentation.viewmodel.UserViewModel
import com.jakewharton.rxbinding2.widget.RxSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class UserActivity : AppCompatActivity(), OnUserClickListener {
    @Inject
    lateinit var factory: UserViewModelFactory
    private val viewModel: UserViewModel by viewModels {
        factory
    }

    private lateinit var binding: AppBarBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.injectU(this)
        super.onCreate(savedInstanceState)
        binding = AppBarBinding.inflate(layoutInflater)
        val view = binding.root
        userAdapter = UserAdapter(this)
        setContentView(view)
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@UserActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }
    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        val queryStream = RxSearchView.queryTextChanges(searchView)
            .map { query ->
                query.toString()
            }
            .debounce(700, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
        queryStream.subscribe({
            if (it.isNotEmpty()) {
                Timber.e(it)
                getSearchUser(it)
            }
        }, {
            Timber.e("$it")
        })
        return true
    }
    private fun getSearchUser(q: String) {
        viewModel.getSearchUser(q).observe(this, { user ->
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
                        userAdapter.setData(user.data)
                    }
                    is Resource.Error -> {
                        binding.rvUser.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.viewStarter.root.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            user.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    override fun onUserClicked(name: String) {
        val intent = Intent(this, RepositoriesActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }
}
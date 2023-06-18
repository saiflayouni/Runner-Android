package com.example.runner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.runner.adapters.ArticlesAdapter
import com.example.runner.model.Status
import com.example.runner.utils.HelperFunctions.toastMsg
import com.example.runner.viewmodel.ArticleViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val articlesVM: ArticleViewModel by viewModels()

    private lateinit var articlesRV: RecyclerView

    private lateinit var swipeContainer: SwipeRefreshLayout

    private lateinit var articlesLm: LinearLayoutManager
    private lateinit var articlesAdapter: ArticlesAdapter

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private var pageNumber: Int = 1
    private var pagedItemsCount: Int = 0
    private var totalPages: Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articlesRV = view.findViewById(R.id.articlesRV)
        swipeContainer = view.findViewById(R.id.swipeContainer)

        articlesAdapter = ArticlesAdapter(mutableListOf())
        articlesLm = LinearLayoutManager(view.context)

        articlesRV.apply {
            adapter = articlesAdapter
            layoutManager = articlesLm
        }

        loadData(pageNumber)

        swipeContainer.setOnRefreshListener {
            articlesAdapter.clear()
            loadData(pageNumber)
            articlesRV.refreshDrawableState()
        }

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.black,
        )

        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {

        scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                if (
                    linearLayoutManager.findLastCompletelyVisibleItemPosition() == recyclerView.layoutManager?.itemCount?.minus(
                        1
                    )
                    &&
                    pageNumber < totalPages
                ) {
                    pageNumber++
                    loadData(pageNumber, false)
                }
            }
        }

        articlesRV.addOnScrollListener(scrollListener)
    }

    private fun loadData(page: Int, initialRun: Boolean? = true) {
        articlesVM.loadRssArticles(page).observe(requireActivity()) {
            it?.let { rs ->
                when (rs.status) {
                    Status.SUCCESS -> {
                        rs.data?.apply {
                            pagedItemsCount = this.pageSize
                            pageNumber = this.page
                            totalPages = this.pages
                            if (initialRun == true) swipeContainer.isRefreshing = false
                            articlesAdapter.addAll(this.articles)
                        }
                    }
                    Status.LOADING -> {
                        if (initialRun == true)
                            swipeContainer.isRefreshing = true

                    }
                    Status.ERROR -> {
                        if (initialRun == true) swipeContainer.isRefreshing = false
                        toastMsg(view?.context!!, it.message!!)
                    }
                }
            }
        }
    }
}
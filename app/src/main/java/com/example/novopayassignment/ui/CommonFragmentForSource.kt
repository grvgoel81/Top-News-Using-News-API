package com.example.novopayassignment.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.novopayassignment.R
import com.example.novopayassignment.model.ArticleResponse
import com.example.novopayassignment.model.Articles
import com.example.novopayassignment.ui.adapter.NewsListAdapter
import com.example.novopayassignment.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_article.*


class CommonFragmentForSource : Fragment() {

    var position = 0
    private var newsSource: String? = null
    private var textView: TextView? = null

    fun getInstance(position: Int): Fragment? {
        val bundle = Bundle()
        bundle.putInt("pos", position)
        val tabFragment = CommonFragmentForSource()
        tabFragment.arguments = bundle
        return tabFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments!!.getInt("pos")
        val mainActivity: MainActivity = activity as MainActivity
        newsSource = mainActivity.getNewsSource(position)
    }

    private fun getAriclesBySources(newsSource: String) {
        val articlesViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        articlesViewModel.getArticlesList(newsSource).observe(this, Observer { articleResponse ->
            setUpAdapter(articleResponse)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Position", position.toString())
    }

    private fun setUpAdapter(articleResponse: ArticleResponse) {
        recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = activity?.baseContext?.let {
                NewsListAdapter(
                    it,
                    articleResponse.articles as ArrayList<Articles>
                )
            }
            addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }


    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            newsSource?.let { getAriclesBySources(it) }
        }
    }
}
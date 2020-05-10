package com.example.novopayassignment.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.novopayassignment.BaseApplication
import com.example.novopayassignment.di.ApiComponent
import com.example.novopayassignment.model.ArticleResponse
import com.example.novopayassignment.model.SourceResponse
import com.example.novopayassignment.network.api.NewsApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository {

    var sourceListLiveData: MutableLiveData<SourceResponse> = MutableLiveData()
    var articleListLiveData: MutableLiveData<ArticleResponse> = MutableLiveData()
    var api_key = "c7e0c06c41744d8089d5a8b716502fef"

    init {
        val apiComponent: ApiComponent = BaseApplication.app.mApiComponent
        apiComponent.inject(this)
    }

    @Inject
    lateinit var newsApi: NewsApi
    private val compositeDisposable = CompositeDisposable()

    private var mSourceListObservable: Observable<SourceResponse>? = null
    private var mArticleListObservable: Observable<ArticleResponse>? = null
    val sourceData: MutableLiveData<SourceResponse>
        get() = sourceListLiveData

    val articleData: MutableLiveData<ArticleResponse>
        get() = articleListLiveData

    fun getSourcesList() {
        mSourceListObservable = newsApi.getSourceList("en", "us", api_key)
        compositeDisposable.add(
            mSourceListObservable!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<SourceResponse>() {
                    override fun onNext(sourceResponse: SourceResponse) {
                        sourceListLiveData.postValue(sourceResponse)
                    }

                    override fun onError(e: Throwable) {
                        sourceListLiveData.postValue(null)
                    }

                    override fun onComplete() {

                    }
                })
        )
    }

    fun getArticlesBySources(source: String): MutableLiveData<ArticleResponse> {
        mArticleListObservable = newsApi.getArticles(source, api_key)
        compositeDisposable.add(
            mArticleListObservable!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArticleResponse>() {
                    override fun onNext(articleResponse: ArticleResponse) {
                        articleListLiveData.postValue(articleResponse)
                    }

                    override fun onError(e: Throwable) {
                        articleListLiveData.postValue(null)
                    }

                    override fun onComplete() {

                    }
                })
        )

        return articleListLiveData
    }
}
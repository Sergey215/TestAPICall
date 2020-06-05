package com.vshcherban.testapicall

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vshcherban.testapicall.gateway.rx.RxJavaApiClient
import com.vshcherban.testapicall.gateway.rx.RxJavaApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_corutines.*

class RetrofitRxJavaActivity: AppCompatActivity() {

    private var postAdapter: PostAdapter? = null
    private val apiService: RxJavaApiService = RxJavaApiClient.apiService
    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        initAdapter()

        compositeDisposable.add(apiService.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ posts ->
               postAdapter?.updateData(posts)
            }, { error->
               log("Error -> ${error.message}")
            }))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


    private fun initAdapter() {
        postAdapter = PostAdapter { post ->
            log("Item click -> ID: ${post.id}  ---- Title: ${post.title}")
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = postAdapter
    }

    private fun log(message: String) {
        Log.d("Debug", message)
    }
}

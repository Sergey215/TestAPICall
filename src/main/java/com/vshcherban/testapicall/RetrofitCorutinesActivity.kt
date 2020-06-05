package com.vshcherban.testapicall

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vshcherban.testapicall.gateway.corutines.CorutinesApiClient
import com.vshcherban.testapicall.gateway.corutines.CorutinesApiService
import kotlinx.android.synthetic.main.activity_corutines.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RetrofitCorutinesActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    private var postAdapter: PostAdapter? = null
    private val apiService: CorutinesApiService = CorutinesApiClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corutines)

        initAdapter()

        launch {
            val result = withContext(Dispatchers.Default) { apiService.getPosts() }
            postAdapter?.updateData(result)
        }

    }

    private fun initAdapter() {
        postAdapter = PostAdapter { post ->
            log("Item click -> ID: ${post.id}  ---- Title: ${post.title}")
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = postAdapter
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private fun log(message: String) {
        Log.d("Debug", message)
    }
}
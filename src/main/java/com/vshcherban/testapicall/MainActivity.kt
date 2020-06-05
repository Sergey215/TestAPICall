package com.vshcherban.testapicall

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vshcherban.testapicall.gateway.corutines.CorutinesApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val job = Job()

    val apiService: CorutinesApiService

    init {
        val logger = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder().addInterceptor(logger)
        logger.level = HttpLoggingInterceptor.Level.BODY

        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client.build())
                .build()

        apiService = retrofit.create(CorutinesApiService::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            val result = withContext(Dispatchers.Default) { apiService.getPosts() }
            result
        }

        retrofitCallbackActivity.setOnClickListener {
            startActivity(Intent(this, RetrofitCallbackActivity::class.java))
        }

        retrofitCorutinesActivity.setOnClickListener {
            startActivity(Intent(this, RetrofitCorutinesActivity::class.java))
        }

        retrofitRxActivity.setOnClickListener {
            startActivity(Intent(this, RetrofitRxJavaActivity::class.java))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
}

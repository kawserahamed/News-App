package org.primeit.newsapp.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.primeit.newsapp.adapter.MyAdapter
import org.primeit.newsapp.adapter.NewsItemClicked
import org.primeit.newsapp.databinding.ActivityMainBinding
import org.primeit.newsapp.model.NewsModel

class MainActivity : AppCompatActivity(), NewsItemClicked {

    lateinit var myAdapter: MyAdapter
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerId.layoutManager = LinearLayoutManager(this)
        fetchData()
        myAdapter = MyAdapter(applicationContext, this)
        binding.recyclerId.adapter = myAdapter


    }

    fun fetchData() {
        val queue = Volley.newRequestQueue(this)
        val url =
            "https://newsapi.org/v2/top-headlines?country=us&category=technology&apiKey=cae30c7ef8bb4afc91f685a7f1c2eded"
        val getRequest: JsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                Log.e("sdsadas", "$it")
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<NewsModel>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = NewsModel(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )

                    newsArray.add(news)
                }
                myAdapter.updateNews(newsArray)
            },
            Response.ErrorListener { error ->

            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params
            }
        }
        queue.add(getRequest)
    }

    override fun onItemClicked(item: NewsModel) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url));

    }


}
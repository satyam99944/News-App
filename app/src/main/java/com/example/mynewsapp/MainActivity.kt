package com.example.mynewsapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.reflect.Method
import kotlin.math.log
import com.example.mynewsapp.MySingleton.Companion as MySingleton

class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdopter:NewsAdoper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            recycler_view.layoutManager=LinearLayoutManager(this)
        fetchData()
        val mAdoper=NewsAdoper(this)
        recycler_view.adapter=mAdoper
    }
//    private fun fatch():ArrayList<String>{
//        val list=ArrayList<String>()
//        for(i in 0..10)
//            list.add("item $i")
//        return list
//    }

//    private fun fatch(){
//   // val url="http://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=24eb9aef6c3e408c9038cd38ad7f877b"
//    val url = "http://newsapi.org/v2/top-headlines?country=be&apiKey=24eb9aef6c3e408c9038cd38ad7f877b"
//    val jsonObjectRequest = JsonObjectRequest(
//        Request.Method.GET, url, null,
//        Response.Listener {
//            val newsJsonArray = it.getJSONArray("articles")
//            val newsArray=ArrayList<News>()
//            for(i in 0 until newsJsonArray.length()){
//                val newsJsonObject=newsJsonArray.getJSONObject(i)
//                val news=News(
//                    newsJsonObject.getString("title"),
//                    newsJsonObject.getString("author"),
//                    newsJsonObject.getString("url"),
//                    newsJsonObject.getString("urlToImage"))
//                newsArray.add(news)
//            }
//            mAdopter.update(newsArray)
//        },
//        Response.ErrorListener { error ->
//
//            Log.d("error","something went wrong")
//        }
//    )
//
//// Access the RequestQueue through your singleton class.
//    MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
//}

    private fun fetchData() {
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=science&piKey=1f4a12d2698e432ea9cf18126dcc7acd"
        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener {
                    val newsJsonArray = it.getJSONArray("articles")
                    val newsArray = ArrayList<News>()
                    for(i in 0 until newsJsonArray.length()) {
                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                        val news = News(
                                newsJsonObject.getString("title"),
                                newsJsonObject.getString("author"),
                                newsJsonObject.getString("url"),
                                newsJsonObject.getString("urlToImage")
                        )
                        newsArray.add(news)
                    }

                    mAdopter.update(newsArray)
                },
                Response.ErrorListener {

                }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    override fun onItemClicked(item: News) {
        Toast.makeText(this,"$item",Toast.LENGTH_SHORT).show()
    }
}
package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.room.movieDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    companion object{
        val TAG = "MainActivity"
    }
    val db by lazy { movieDB(this) }
    lateinit var movieAdapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()
        setReccyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val movies = db.movieDao().getMovies()
            Log.d(TAG, "data : ${movies}")
            withContext(Dispatchers.Main){
                movieAdapter.setData(movies)
            }
        }
    }
    fun setupListener(){
        tambahMovie.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }
    private fun setReccyclerView(){
        movieAdapter = MovieAdapter(arrayListOf())
        list_movie.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
        }
    }
}
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.room.movie
import com.example.myapplication.room.movieDB
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    val db by lazy { movieDB(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupListener()
    }
    fun setupListener(){
        btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().addMovies(
                    movie(
                        0,
                        etTitle.text.toString(),
                        etDesc.text.toString()
                    )
                )
                finish()
            }
        }
    }
}
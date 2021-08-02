package com.example.movies_app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieScreen:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_page)

        var id=findViewById<TextView>(R.id.id)
        var overview=findViewById<TextView>(R.id.overview)
        var header=findViewById<ImageView>(R.id.image)
        var title=findViewById<TextView>(R.id.title2)
        val intent=getIntent()

        id.text = intent.getStringExtra("c")
        overview.text = intent.getStringExtra("d")
        title.text=intent.getStringExtra("a")
        val url="https://image.tmdb.org/t/p/w300"+intent.getStringExtra("b")
        Glide.with(this).load(url).into(findViewById(R.id.image))



    }
}
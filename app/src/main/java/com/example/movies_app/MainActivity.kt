package com.example.movies_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter:NoteAdapter
    var movieViewModel:MovieViewModel?=null

    companion object{
        var num:Int=0
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieViewModel = ViewModelProvider(this, MovieViewModelFactory(MovieRepository(application))).get(
            MovieViewModel::class.java
        )
        getData()

    }

    private fun getData() {
        val data=dbservice.dbService.getData("english",1)
        data.enqueue(object:Callback<apidata>{
            override fun onResponse(call: Call<apidata>, response: Response<apidata>) {
                val result=response.body()
                if(result!=null)
                {
                    Log.d("choker", "ggg")
                    println(result.results.size)
                    adapter=NoteAdapter(this@MainActivity,result.results)
                    var movieList=findViewById<RecyclerView>(R.id.movie_list)
                    movieList.adapter=adapter
                    movieList.layoutManager=GridLayoutManager(this@MainActivity,2)

                    for(i in result.results)
                    {
                        if(i!=null)
                        {
                          //  println(i.title+"    "+i.poster_path)
                            num++
                        movieViewModel!!.insert(Note(num,i.title,i.overview,i.poster_path))

                        }

                    }


                    adapter.setOnItemClickListener(
                        object : NoteAdapter.OnItemClickListener{
                            override fun onItemClick(note: Result_Data)
                            {
                                //val data = Intent()
                                val data = Intent(this@MainActivity, MovieScreen::class.java)
                                data.putExtra("a", note.title)
                                data.putExtra("b", note.poster_path)
                                data.putExtra("c", note.id)
                                data.putExtra("d", note.overview)
                                startActivity(data)

                            }
                        }
                    )
                }
            }

            override fun onFailure(call: Call<apidata>, t: Throwable) {
                println("lost")
               // Log.d("choker","wrong",t)
            }
        })
    }
   


}
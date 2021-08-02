package com.example.movies_app

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL ="https://api.themoviedb.org/"
const val API_KEY ="6118e871eac6c7274059bcd7146d3000"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface dbserviceInterface {

    @GET(value="/3/movie/popular?api_key=$API_KEY")
    fun getData(@Query("language")language : String,@Query("page")page:Int):Call<apidata>
}

object dbservice{

     val dbService: dbserviceInterface

    init{
            val retorfit =Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        dbService=retorfit.create(dbserviceInterface::class.java)
    }


    }
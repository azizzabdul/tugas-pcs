package com.example.adajaapps.data.remote

import com.example.adajaapps.data.model.NewsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/api/?i=onions,garlic&q=omelet&p=3/")
    fun listNews() : Call<NewsList>
//    fun listNews(@Query("i") i : String, @Query("q") q : String, @Query("p") p : Int) : Call<NewsList>

    @GET("detail/")
    fun detailNews(@Query("url") url : String): Call<NewsList>

    @GET("search/")
    fun searchNews(@Query("q") url : String): Call<NewsList>
}

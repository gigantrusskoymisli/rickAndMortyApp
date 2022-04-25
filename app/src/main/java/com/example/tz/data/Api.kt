package com.example.tz.data

import com.example.tz.Character
import com.example.tz.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("character")
    fun getCharacterList(): Call<Character>

    @GET("character/?")
    fun getCharacterPage(@Query("page") pageId: String): Call<Character>

    @GET("character/{id}")
    fun getSingleCharacter(@Path("id") id: String) : Call<Result>
}
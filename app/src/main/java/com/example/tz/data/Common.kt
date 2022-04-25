package com.example.tz.data

object Common {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    val api:Api = Client.getClient(BASE_URL).create(Api::class.java)
}
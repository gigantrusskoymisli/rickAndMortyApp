package com.example.tz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tz.data.Api
import com.example.tz.data.Common
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var api: Api
    lateinit var adapter: ListAdapter
    lateinit var dialog: AlertDialog
    private lateinit var characters: Character
    private lateinit var recyclerView : RecyclerView
    private lateinit var buttonPrev: Button
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        api = Common.api
        recyclerView= findViewById(R.id.listView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        buttonPrev = findViewById(R.id.prev)
        buttonPrev.setOnClickListener{
            var pageId: Any? = characters.info.prev
            if (pageId == null) {
                Toast.makeText(it.context, "ты на первой странице", Toast.LENGTH_SHORT).show()
            }
            else {
                pageId = pageId.toString().substring(48)
                getCharacterPage(pageId)
            }
        }

        buttonNext = findViewById(R.id.next)
        buttonNext.setOnClickListener {
            var pageId: Any? = characters.info.next
            if (pageId == null) {
                Toast.makeText(it.context, "ты на последней странице", Toast.LENGTH_SHORT).show()
            }
            else {
                pageId = pageId.toString().substring(48)
                getCharacterPage(pageId as String)
            }
        }
        getCharacters()
    }


    private fun getCharacters() {
        dialog.show()
        api.getCharacterList().enqueue(object : Callback<Character> {
            override fun onFailure(call: Call<Character>, t: Throwable) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                characters = response.body() as Character
                adapter = ListAdapter(baseContext, characters)
                adapter.notifyDataSetChanged()
                recyclerView = findViewById(R.id.listView)
                recyclerView.adapter = adapter

                dialog.dismiss()

            }
        })
    }

    private fun getCharacterPage(pageId: String) {
        dialog.show()
        api.getCharacterPage(pageId).enqueue(object : Callback<Character>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                characters = response.body() as Character
                adapter = ListAdapter(baseContext, characters)
                adapter.notifyDataSetChanged()
                recyclerView = findViewById(R.id.listView)
                recyclerView.adapter = adapter

                dialog.dismiss()
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {

            }

        })
    }
}
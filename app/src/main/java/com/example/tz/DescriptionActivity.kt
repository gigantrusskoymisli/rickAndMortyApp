package com.example.tz

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.tz.data.Api
import com.example.tz.data.Common
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DescriptionActivity : AppCompatActivity() {

    lateinit var dialog: AlertDialog
    lateinit var api: Api
    lateinit var result: Result
    lateinit var imageView: ImageView
    lateinit var nameText: TextView
    lateinit var genderText: TextView
    lateinit var raceText: TextView
    lateinit var statusText: TextView
    lateinit var lastPlaceText: TextView
    lateinit var episodesText: TextView

    companion object {

        const val ID = "ID"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()
        api = Common.api
        imageView = findViewById(R.id.image_desc)
        nameText = findViewById(R.id.name_desc)
        genderText = findViewById(R.id.gender_desc)
        raceText = findViewById(R.id.race_desc)
        statusText = findViewById(R.id.status_desc)
        lastPlaceText = findViewById(R.id.last_place_desc_edit)
        episodesText = findViewById(R.id.episodes_edit)

        val id = intent.getStringExtra(ID)!!
        getCharacter(id)

    }

    private fun getCharacter(id: String) {
        dialog.show()
        api.getSingleCharacter(id).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                result = response.body() as Result
                Picasso.get().load(result.image).into(imageView)
                nameText.text = result.name
                genderText.text = result.gender
                raceText.text = result.species
                statusText.text = result.status
                lastPlaceText.text = result.location.name
                episodesText.text = result.episode.size.toString()

                dialog.dismiss()
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }

        })
    }
}
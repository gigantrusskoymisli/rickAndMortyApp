package com.example.tz

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog

class ListAdapter(
    private val context : Context,
    private val characterList : Character
    ): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)
        val gender: TextView = itemView.findViewById(R.id.gender)
        val race: TextView = itemView.findViewById(R.id.race)
        var id : Int? = null

        fun bind() {

            itemView.setOnClickListener {

                val intent = Intent(it.context,DescriptionActivity::class.java)
                intent.putExtra(DescriptionActivity.ID,id.toString())
                startActivity(it.context,intent,null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = characterList.results.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()

        Picasso.get().load(characterList.results[position].image).into(holder.image)
        holder.name.text = characterList.results[position].name
        holder.gender.text = characterList.results[position].gender
        holder.race.text = characterList.results[position].species
        holder.id = characterList.results[position].id
    }
}
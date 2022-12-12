package com.example.rmretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.rmretrofit.network.Character

class MainAdapter () : RecyclerView.Adapter<MainAdapter.MainViewHolder> (){

    private val charactersList = mutableListOf<Character>()

    fun setList(charactersList: List<Character>) {
        this.charactersList.clear()
        this.charactersList.addAll(charactersList)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    inner class MainViewHolder (private val itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindData (character : Character) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val image = itemView.findViewById<ImageView>(R.id.image)

            name.text = character.name
            Glide.with(itemView)
                .load(character.image)
                .transform(CircleCrop())
                .into(image)
        }
    }

}
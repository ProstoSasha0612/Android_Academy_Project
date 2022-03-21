package com.projectapp.moviesapp.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projectapp.moviesapp.databinding.ViewHolderActorBinding

class ActorsAdapter(private val actorsList: List<Actor>) :
    RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderActorBinding.inflate(layoutInflater)
        return ActorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actorsList[position])
    }

    override fun getItemCount(): Int {
        return actorsList.size
    }

    class ActorsViewHolder(private val binding: ViewHolderActorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: Actor) {
            with(binding) {
                nameTv.text = actor.name
                //TODO use here Glide to loading images in future
            }
        }
    }
}
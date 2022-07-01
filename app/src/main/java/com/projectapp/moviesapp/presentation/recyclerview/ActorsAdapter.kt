package com.projectapp.moviesapp.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.projectapp.moviesapp.data.model.Actor
import com.projectapp.moviesapp.databinding.ViewHolderActorBinding

class ActorsAdapter(private val actorsList: List<Actor>?) :
    RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderActorBinding.inflate(layoutInflater)
        return ActorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        actorsList?.let { holder.bind(actorsList[position]) }
    }

    override fun getItemCount(): Int {
        return actorsList?.size ?: 0
    }

    class ActorsViewHolder(private val binding: ViewHolderActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) {
            Glide.with(binding.root).load(actor.imageUrl).into(binding.photoIv)
            binding.nameTv.text = actor.name
        }
    }
}
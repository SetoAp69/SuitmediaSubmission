package com.excal.suitmediasubmission.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.excal.suitmediasubmission.databinding.ItemRowUserBinding

class UserAdapter(private val listUser:List<User>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    var listener:onItemClick?=null
    private lateinit var binding:ItemRowUserBinding
    inner class ListViewHolder(var binding:ItemRowUserBinding): RecyclerView.ViewHolder(binding.root){
        val imgPhoto: ImageView = binding.imgItemPhoto
        val tvName: TextView = binding.tvItemName
        val tvDescription: TextView = binding.tvItemEmail

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, email, firstName, lastName, avatar) =listUser[position]

        holder.tvName.text=firstName+" "+lastName
        holder.tvDescription.text=email


        Glide.with(holder.itemView.context)
            .load(avatar)
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener{
            listener?.setOnItemClick(it,listUser[position])
        }

    }
    interface onItemClick{
        fun setOnItemClick(view: View, user:User)
    }
}
package com.example.endemicsid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.endemicsid.databinding.ItemRowAnimalBinding

class ListHewanAdapter(private val listHewan: ArrayList<Hewan>) : RecyclerView.Adapter<ListHewanAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowAnimalBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nama, ilmiah, deskripsi, foto) = listHewan[position]
        holder.binding.tvNamaHewan.text = nama
        holder.binding.tvNamaIlmiah.text = ilmiah
        Glide.with(holder.itemView.context)
            .load(foto)
            .centerCrop()
            .into(holder.binding.ivFotoHewan)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listHewan[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listHewan.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Hewan)
    }
}
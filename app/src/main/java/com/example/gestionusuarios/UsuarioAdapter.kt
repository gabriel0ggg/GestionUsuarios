package com.example.gestionusuarios

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.gestionusuarios.databinding.ItemUsuarioBinding

class UsuarioAdapter(private val usuarios : List<Usuario>, private val listener: OnClickListener)
    : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_usuario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = usuarios.get(position)
        with(holder) {
            setListener(usuario, position+1)
            binding.tvOrdenar.text = usuario.id.toString()
            binding.tvNombre.text = usuario.getFullName()
            Glide.with(context)
                .load(usuario.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgFoto)
        }
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUsuarioBinding.bind(view)

        fun setListener(usuario : Usuario, position: Int) {
            binding.root.setOnClickListener { listener.onClick(usuario, position) }
        }
    }
}
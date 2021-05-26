package com.example.gestionusuarios

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionusuarios.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var usuarioAdapter: UsuarioAdapter
    private lateinit var linearLayoutManager : RecyclerView.LayoutManager
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $isFirstTime")

        if (isFirstTime) {
            MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.dialog_title)
                    .setPositiveButton(R.string.dialog_confirm, {dialogInterface, i ->
                        preferences.edit().putBoolean(getString(R.string.sp_first_time), false).commit()
                    })
                    .setNegativeButton("Cancelar", null)
                    .show()
        }


        usuarioAdapter = UsuarioAdapter(getUsuarios(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.lista.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = usuarioAdapter
        }
    }

    private fun getUsuarios() : MutableList<Usuario> {
        val usuarios = mutableListOf<Usuario>()
        val hugo = Usuario(1, "Hugo", "Zambada", "https://upload.wikimedia.org/wikipedia/commons/d/d4/Hugo-Herrera.jpg")
        val paco = Usuario(2, "Paco", "Carrillo", "https://s.yimg.com/ny/api/res/1.2/nYVexDD8Nq1Nx6oT_s6uWA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTk2MDtoPTU3NS42MjU-/https://s.yimg.com/uu/api/res/1.2/i0CU.6fIcQIFdkskWbQBpw--~B/aD02MTQ7dz0xMDI0O2FwcGlkPXl0YWNoeW9u/https://media.zenfs.com/es/animal_mx_205/d4fc6d1cf9e908a2186cd9022d398305")
        val luis = Usuario(3, "Luis", "Palma", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Paco_Obregón.JPG/1200px-Paco_Obregón.JPG")

        usuarios.add(hugo)
        usuarios.add(paco)
        usuarios.add(luis)
        usuarios.add(hugo)
        usuarios.add(paco)
        usuarios.add(luis)
        usuarios.add(hugo)
        usuarios.add(paco)
        usuarios.add(luis)
        usuarios.add(hugo)
        usuarios.add(paco)
        usuarios.add(luis)

        return usuarios
    }

    override fun onClick(usuario: Usuario, position : Int) {
        Toast.makeText(this, "$position ${usuario.getFullName()}", Toast.LENGTH_SHORT)
            .show()
    }
}
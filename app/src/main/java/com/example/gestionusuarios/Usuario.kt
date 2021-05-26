package com.example.gestionusuarios

data class Usuario (val id : Long, var name : String, var lastName : String, var url : String) {

    fun getFullName() : String = "$name $lastName"
}


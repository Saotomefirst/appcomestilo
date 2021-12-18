package com.saotome.appcomestilo

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContatoHelper(private val sharedPreferences: SharedPreferences) {

    fun getListaContatos(): List<Contato> {
        val lista = sharedPreferences.getString("contatos", "[]")
        val tipoConversor = object: TypeToken<List<Contato>>() {}.type
        return Gson().fromJson(lista, tipoConversor)
    }

    fun setListaContatos(lista: List<Contato>) {
        sharedPreferences.edit().putString("contatos", Gson().toJson(lista)).commit()
    }
}
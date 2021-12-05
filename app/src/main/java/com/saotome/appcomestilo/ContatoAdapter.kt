package com.saotome.appcomestilo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContatoAdapter : RecyclerView.Adapter <ContatoAdapter.ContatoAdapterViewHolder> (){

    private val lista: MutableList<Contato> = mutableListOf()

    //quem cria cada item visual da tela
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoAdapterViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.contato_item, parent, false)
        return ContatoAdapterViewHolder(view)
    }

    // quem percorre a lista e preenche cada item na tela
    override fun onBindViewHolder(holder: ContatoAdapterViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    // Classe que faz o gerenciamento individual dos itens
    class ContatoAdapterViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {

        // Aqui temos a declaração de cada elemento a ser montado dentro de um Card

        fun bind (contato: Contato) {

        }
    }

}
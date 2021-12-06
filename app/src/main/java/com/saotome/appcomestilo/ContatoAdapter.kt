package com.saotome.appcomestilo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

    //Método público que acessa o Adapter
    fun updateList(listaAtualizada: List<Contato>) {
        lista.clear()
        lista.addAll(listaAtualizada)
        notifyDataSetChanged()
    }

    // Classe que faz o gerenciamento individual dos itens
    class ContatoAdapterViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {

        // Aqui temos a declaração de cada elemento a ser montado dentro de um Card
        private val tvNome: TextView = itemView.findViewById(R.id.tv_nome)
        private val tvTelefone: TextView = itemView.findViewById(R.id.tv_telefone)
        private val ivFoto: ImageView = itemView.findViewById(R.id.iv_foto)

        fun bind (contato: Contato) {
            tvNome.text = contato.nome
            tvTelefone.text = contato.telefone
            //TODO("Atualizar a Fotografia")
        }
    }

}
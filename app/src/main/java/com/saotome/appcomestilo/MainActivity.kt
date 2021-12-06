package com.saotome.appcomestilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //Obtemos o componente RecyclerView do XML
    private val rvLista: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_lista)
    }

    private val adapter = ContatoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
        updateList()
    }

    fun bindView() {
        // Definimos o adapter do componente da tela como sendo o que criamos
        rvLista.adapter = adapter

        // Definimos o layout manager
        rvLista.layoutManager = LinearLayoutManager(this)
    }

    private fun updateList () {
        // Criamos uma lista mock apenas para vermos dados na tela
        adapter.updateList(
            arrayListOf<Contato>(
                Contato(
                    nome = "Jorge Freitas",
                    telefone = "(21)987-654-321",
                    foto = "foto.png"
                ),
                Contato(
                    nome = "Thaís Gallo",
                    telefone = "(21)123-456-789",
                    foto = "foto2.png"
                ),
                Contato(
                    nome = "Marilena Lopes",
                    telefone = "(21)123-654-987",
                    foto = "foto3.png"
                ),
            )
        )
    }

    // Método para colocar o menu na tela
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun mostrarToast (mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_1 -> {
                mostrarToast ("Menu 1 Clicado")
                return true
            }
            R.id.item_menu_2 -> {
                mostrarToast ("Segundo Menu clicado")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
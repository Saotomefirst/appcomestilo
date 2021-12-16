package com.saotome.appcomestilo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saotome.appcomestilo.ContatoDetalhe.Companion.CONTATO_EXTRA

class MainActivity : AppCompatActivity(), ClickItemContatoListener {

    //Obtemos o componente RecyclerView do XML
    private val rvLista: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_lista)
    }

    private val adapter = ContatoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        inicializarDrawer()
        bindView()
        updateList()
    }

    //Inicialização manual do Drawer Layout
    private fun inicializarDrawer () {
        //TODO atualizar parâmetros para ViewBindindg!
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        //val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
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

    override fun clickItemContato(contato: Contato) {
        // Mínimo necessário para se iniciar a nova Activity
        val intent = Intent(this, ContatoDetalhe::class.java)
        intent.putExtra(CONTATO_EXTRA, contato)
        startActivity(intent)
    }


}
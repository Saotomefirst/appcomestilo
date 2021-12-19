package com.saotome.appcomestilo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saotome.appcomestilo.ContatoDetalhe.Companion.CONTATO_EXTRA
import com.saotome.appcomestilo.databinding.ActivityMainBinding
import com.saotome.appcomestilo.databinding.DrawerMenuBinding

class MainActivity : AppCompatActivity(), ClickItemContatoListener {

    //Obtemos o componente RecyclerView do XML
    private val rvLista: RecyclerView by lazy {
        //findViewById<RecyclerView>(R.id.rv_lista)
        activityMainBinding.rvLista
    }

    private val adapter = ContatoAdapter(this)
    private lateinit var drawerMenuBinding: DrawerMenuBinding
    private lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drawerMenuBinding = DrawerMenuBinding.inflate(layoutInflater)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)


        val drawerMenuView = drawerMenuBinding.root
        setContentView(drawerMenuView)

        val activityMainView = activityMainBinding.root
        setContentView(activityMainView)

        //setContentView(R.layout.drawer_menu)

        inicializarDrawer()
        fetchListaContatos()
        bindView()

    }

    // Função que simula a chamada de uma API que carrega os dados de forma remota
    private fun fetchListaContatos () {
        val lista = arrayListOf<Contato>(
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

        val contatoHelper = ContatoHelper(getInstanceSharedPreferences())
        contatoHelper.setListaContatos(lista)

        /*
        // Salvando no Shared Preferences
        getInstanceSharedPreferences().edit () {
            val json = Gson().toJson(lista)
            putString("contatos", json)

            /*
            apply X commit
            Ambos confirmam a gravação dos dados no SharedPreferences
            apply: abre uma thread separada
            commit: bloqueia a thread atual
             */
            commit()
        }

         */
    }

    private fun getInstanceSharedPreferences (): SharedPreferences {
        return getSharedPreferences("com.saotome.appcomestilo.PREFERENCIAS", MODE_PRIVATE)
    }

    //Inicialização manual do Drawer Layout
    private fun inicializarDrawer () {
        val drawerLayout = drawerMenuBinding.drawerLayout
        //val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        //val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)

        val toolbar = activityMainBinding.toolbar
        //val toolbar = findViewById<Toolbar>(R.id.toolbar)
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
        updateList()
    }

    private fun getListaContatos () : List<Contato>{

        val contatoHelper = ContatoHelper(getInstanceSharedPreferences())
        return contatoHelper.getListaContatos()
        /*
        // Nota: GetString exige 2 parametros, um com o nome da chave a ser procurada
        // e o outro um valor padrao caso a chave não seja localizada
        // Neste caso, se não acharmos nada, retornamos uma lista vazia
        val lista = getInstanceSharedPreferences().getString("contatos", "[]")
        val tipoConversor = object: TypeToken<List<Contato>>() {}.type
        return Gson().fromJson(lista, tipoConversor)

         */
    }

    private fun updateList () {
        // Criamos uma lista mock apenas para vermos dados na tela
        val lista = getListaContatos()
        adapter.updateList(lista)
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
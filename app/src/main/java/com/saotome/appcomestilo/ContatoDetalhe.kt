package com.saotome.appcomestilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout

class ContatoDetalhe : AppCompatActivity() {

    private var contato : Contato? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato_detalhe)

        initToolbar ()
        getExtra()
        bindViews()
    }

    private fun initToolbar () {
        //TODO atualizar parâmetros para ViewBindindg!

        //val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // exibe o botão Voltar [ < ] na toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun getExtra() {
        contato = intent.getParcelableExtra(CONTATO_EXTRA)
    }

    private fun bindViews () {
        findViewById<TextView>(R.id.tv_nome).text = contato?.nome
        findViewById<TextView>(R.id.tv_telefone).text = contato?.telefone
    }

    companion object {
        const val CONTATO_EXTRA : String = "CONTATO_EXTRA"
    }

    // para o botao voltar [ < ] efetivamente encerrar a activity e voltar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
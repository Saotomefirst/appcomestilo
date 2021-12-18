package com.saotome.appcomestilo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ApplicationProvider
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ContatoHelperTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val sharedPreferences =
        context.getSharedPreferences("com.saotome.appcomestilo.PREFERENCIAS",
            AppCompatActivity.MODE_PRIVATE)
    private val contatoHelper = ContatoHelper(sharedPreferences)

    @Test
    fun `Quando chamar o metodo getListaContatos() com 2 Contatos, deve retornar uma lista de 2 contatos`() {
        // Sessão de Preparação
        mockListaDoisContatos()

        // Sessão de Validação
        val lista = contatoHelper.getListaContatos()
        assertEquals(2, lista.size)
    }

    @Test
    fun `Quando chamar o metodo getListaContatos() sem contatos, deve retornar uma lista vazia` () {
        // Sessão de Preparação
        mockListaContatosVazia()

        // Sessão de Validação
        val lista = contatoHelper.getListaContatos()
        assertEquals(0, lista.size)
    }
    private fun mockListaDoisContatos () {
        contatoHelper.setListaContatos(
            arrayListOf<Contato>(
                Contato("Jaqueline Villa", "(11) 1111-1111", "jaqueline.jpg"),
                Contato("Raquel Silva", "(22) 2222-2222", "raquel.jpg")
            )
        )
    }

    private fun mockListaContatosVazia () {
        contatoHelper.setListaContatos(arrayListOf<Contato>())
    }
}
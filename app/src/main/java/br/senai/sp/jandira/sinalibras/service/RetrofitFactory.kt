package br.senai.sp.jandira.sinalibras.service

import br.senai.sp.jandira.service.UsuarioService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "http://10.107.134.18:8080/"//troquei o localhost pro ip do meu pc para conseguir testar a aplicação no meu celular
    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun  getUsuarioService(): UsuarioService{
        return retrofitFactory.create(UsuarioService::class.java)
    }



}
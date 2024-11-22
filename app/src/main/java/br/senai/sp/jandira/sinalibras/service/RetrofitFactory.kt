package br.senai.sp.jandira.sinalibras.service

import br.senai.sp.jandira.service.UsuarioService
import br.senai.sp.jandira.service.PostagensService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    //private val BASE_URL="http://10.107.140.8:8080/"//marcel
    //private val BASE_URL="http://192.168.46.26:8080/"//CELULAR
    private val BASE_URL = "http://192.168.15.5:8080/"  //CASA
//private val BASE_URL = "https://sinalibras-back-d2gnehfaaxfxegaq.brazilsouth-01.azurewebsites.net/"  //NUVEM


    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun  getUsuarioService(): UsuarioService{
        return retrofitFactory.create(UsuarioService::class.java)
    }

    fun  getPostagensService(): PostagensService {
        return retrofitFactory.create(PostagensService::class.java)
    }

    fun  getQuizService(): QuizService{
        return retrofitFactory.create(QuizService::class.java)
    }

    private val BASE_URL_EMAIL = "https://api.captainverify.com/v2/"
    private val retrofitFactoryEmail = Retrofit
        .Builder()
        .baseUrl(BASE_URL_EMAIL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun  getEmailValidoService(): EmailValidoService{
        return retrofitFactoryEmail.create(EmailValidoService::class.java)
    }



}
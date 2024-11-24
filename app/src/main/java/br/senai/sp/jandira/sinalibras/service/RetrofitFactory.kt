package br.senai.sp.jandira.sinalibras.service

import br.senai.sp.jandira.service.UsuarioService
import br.senai.sp.jandira.service.PostagensService
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {
    //private val BASE_URL="http://10.107.140.8:8080/"//marcel
    //private val BASE_URL="http://192.168.46.26:8080/"//CELULAR
    private val BASE_URL = "http://192.168.15.5:8080/"  //CASA
//private val BASE_URL = "https://sinalibras-back-d2gnehfaaxfxegaq.brazilsouth-01.azurewebsites.net/"  //NUVEM


    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(logging)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(client) // Configuração do cliente HTTP com logging
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
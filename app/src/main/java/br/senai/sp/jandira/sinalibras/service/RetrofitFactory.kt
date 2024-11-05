package br.senai.sp.jandira.sinalibras.service

import br.senai.sp.jandira.service.UsuarioService
import br.senai.sp.jandira.service.VideoAulaService
import br.senai.sp.jandira.sinalibras.model.VideoAula
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val BASE_URL = "http://10.107.140.77:8080/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun  getUsuarioService(): UsuarioService{
        return retrofitFactory.create(UsuarioService::class.java)
    }

    fun  getVideoAulaService(): VideoAulaService {
        return retrofitFactory.create(VideoAulaService::class.java)
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
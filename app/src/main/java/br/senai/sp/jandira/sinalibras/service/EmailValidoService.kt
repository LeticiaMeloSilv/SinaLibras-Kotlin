package br.senai.sp.jandira.sinalibras.service

import br.senai.sp.jandira.sinalibras.model.Email
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EmailValidoService {
    @GET("verify?apikey=xwVhoTc3f5KHnOdAetIc4kp6ihcQT6la")
    fun getEmailValido(@Query("email")email:String): Call<Email>

}
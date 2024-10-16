package br.senai.sp.jandira.sinalibras.service

import br.senai.sp.jandira.sinalibras.model.ResultQuiz
import retrofit2.Call
import retrofit2.http.GET

interface QuizService {
    @GET("/v1/sinalibras/questoes")
    fun getAllQuestoes(): Call<ResultQuiz>

}
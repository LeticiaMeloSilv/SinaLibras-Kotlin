package br.senai.sp.jandira.sinalibras.service

import br.senai.sp.jandira.sinalibras.model.RespostaUsuario
import br.senai.sp.jandira.sinalibras.model.ResultQuiz
import br.senai.sp.jandira.sinalibras.model.ResultRespostaUsuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface QuizService {
    @GET("/v1/sinalibras/questoes")
    fun getAllQuestoes(): Call<ResultQuiz>


    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/resultado_quiz")
    fun salvarRespostas(@Body resultRespostaUsuario: List<RespostaUsuario>): Call<ResultRespostaUsuario>
}
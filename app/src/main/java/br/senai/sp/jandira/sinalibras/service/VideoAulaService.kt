package br.senai.sp.jandira.service

import br.senai.sp.jandira.sinalibras.model.ResultUsuarioTeste
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultModulo
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.model.ResultQuiz
import br.senai.sp.jandira.sinalibras.model.ResultVideo
import br.senai.sp.jandira.sinalibras.model.VideoAula
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface VideoAulaService {

    //GET (retrofit2.http)
    //Call<t>(retrofit2) o tipo de call que temos que escolher ao usar o retrofit
    //Result do nosso pacote (br.senai..)
    // A URL é padrão na API tod, na requisição colocamos apenas o diferente na URL, no caso da url abaixo né a palvra character

    @GET("/v1/sinalibras/modulos")
    fun getAllModulos(): Call<ResultModulo>


    @GET("/v1/sinalibras/videoaula")
    fun getAllVideos(): Call<ResultVideo>

    @GET("/v1/sinalibras/videos/modulo/{id}")
    fun getVideoById(@Path("id")id:Int): Call<ResultVideo>


    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/videoaula")
    fun setSalvarVideoAula(@Body videoAula: VideoAula): Call<ResultVideo>
}
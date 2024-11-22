package br.senai.sp.jandira.service

import br.senai.sp.jandira.sinalibras.model.Comentarios
import br.senai.sp.jandira.sinalibras.model.Postagem
import br.senai.sp.jandira.sinalibras.model.ResultComentario
import br.senai.sp.jandira.sinalibras.model.ResultFeed
import br.senai.sp.jandira.sinalibras.model.ResultModulo
import br.senai.sp.jandira.sinalibras.model.ResultNivel
import br.senai.sp.jandira.sinalibras.model.ResultPostagem
import br.senai.sp.jandira.sinalibras.model.ResultVideo
import br.senai.sp.jandira.sinalibras.model.ResultVideoID
import br.senai.sp.jandira.sinalibras.model.VideoAula
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PostagensService {

    //GET (retrofit2.http)
    //Call<t>(retrofit2) o tipo de call que temos que escolher ao usar o retrofit
    //Result do nosso pacote (br.senai..)
    // A URL é padrão na API tod, na requisição colocamos apenas o diferente na URL, no caso da url abaixo né a palvra character

    @GET("/v1/sinalibras/niveis")
    fun getAllNiveis(): Call<ResultNivel>

    @GET("/v1/sinalibras/modulos")
    fun getAllModulos(): Call<ResultModulo>

    @GET("/v1/sinalibras/feed")
    fun getAllFeed(): Call<ResultFeed>



    @GET("/v1/sinalibras/videoaula")
    fun getAllVideos(): Call<ResultVideo>

    @GET("/v1/sinalibras/videos/modulo/{id}")
    fun getVideosById(@Path("id")id:Int): Call<ResultVideo>

    @GET("/v1/sinalibras/videos/modulo/{id}")
    fun getVideosProfessorById(@Path("id")id:Int): Call<ResultVideo>


    @GET("/v1/sinalibras/videoaula/{id}")
    fun getVideoById(@Path("id")id:Int): Call<ResultVideoID>



    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/videoaula")
    fun setSalvarVideoAula(@Body videoAula: VideoAula): Call<ResultVideo>



    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/videoaula/comentario")
    fun setSalvarComentario(@Body comentario: Comentarios): Call<ResultComentario>


    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/postagem")
    fun setSalvarPostagem(@Body postagem: Postagem): Call<ResultPostagem>



    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/postagem/comentario")
    fun setSalvarComentarioPostagem(@Body comentario: Comentarios): Call<ResultComentario>

}
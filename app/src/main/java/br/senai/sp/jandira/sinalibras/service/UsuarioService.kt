package br.senai.sp.jandira.service

import br.senai.sp.jandira.sinalibras.model.AlunoResponse
import br.senai.sp.jandira.sinalibras.model.Result
import br.senai.sp.jandira.sinalibras.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioService {

    //GET (retrofit2.http)
    //Call<t>(retrofit2) o tipo de call que temos que escolher ao usar o retrofit
    //Result do nosso pacote (br.senai..)
    // A URL é padrão na API tod, na requisição colocamos apenas o diferente na URL, no caso da url abaixo né a palvra character
    //https://rickandmortyapi.com/api/character
    @GET("/v1/sinalibras/alunos")
    fun getAllAlunos(): Call<Result>

    @GET("/v1/sinalibras/professores")
    fun getAllProfessores(): Call<Result>


    @GET("/v1/sinalibras/aluno/{id}")
    fun getAlunoId(@Path("id")id:Int): Call<Usuario>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/aluno")
    fun save(@Body usuario: Usuario): Call<AlunoResponse>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/aluno/validacao")
    fun validaEntrada(@Body usuario: Usuario): Call<Usuario>
}
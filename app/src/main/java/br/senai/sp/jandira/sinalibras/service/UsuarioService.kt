package br.senai.sp.jandira.service

import br.senai.sp.jandira.sinalibras.model.ResultUsuarioTeste
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
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
    fun getAllAlunos(): Call<ResultAluno>

    @GET("/v1/sinalibras/professores")
    fun getAllProfessores(): Call<ResultAluno>


    @GET("/v1/sinalibras/aluno/{id}")
    fun getAlunoId(@Path("id")id:Int): Call<ResultAluno>

    @GET("/v1/sinalibras/professor/{id}")
    fun getProfessorId(@Path("id")id:Int): Call<ResultProfessor>


    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/aluno")
    fun setSalvarAluno(@Body usuario: Aluno): Call<ResultAluno>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/professor")
    fun setSalvarProfessor(@Body usuario: Professor): Call<ResultProfessor>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/usuario")
    fun setSalvarUsuarioTemporario(@Body usuario: Professor): Call<ResultUsuarioTeste>


    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/aluno/validacao")
    fun setValidarEntradaAluno(@Body usuario: Aluno): Call<ResultAluno>


    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/professor/validacao")
    fun setValidarEntradaProfessor(@Body usuario: Professor): Call<ResultProfessor>
}
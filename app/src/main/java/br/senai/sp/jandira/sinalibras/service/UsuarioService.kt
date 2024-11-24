package br.senai.sp.jandira.service

import br.senai.sp.jandira.sinalibras.model.ResultUsuarioTeste
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.model.ResultProfessores
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsuarioService {

    //GET (retrofit2.http)
    //Call<t>(retrofit2) o tipo de call que temos que escolher ao usar o retrofit
    //Result do nosso pacote (br.senai..)
    // A URL é padrão na API tod, na requisição colocamos apenas o diferente na URL, no caso da url abaixo né a palvra character
    @GET("/v1/sinalibras/alunos")
    fun getAllAlunos(): Call<ResultAluno>

    @GET("/v1/sinalibras/aluno/{id}")
    fun getAlunoId(@Path("id")id:Int): Call<ResultAluno>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/aluno")
    fun setSalvarAluno(@Body usuario: Aluno): Call<ResultAluno>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @PUT("/v1/sinalibras/aluno/{id}")
    fun setAtualizarAluno(@Path("id")id:Int,@Body usuario: Aluno): Call<ResultAluno>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/aluno/validacao")
    fun setValidarEntradaAluno(@Body usuario: Aluno): Call<ResultAluno>

    @DELETE("/v1/sinalibras/aluno/{id}")
    fun setDellAluno(@Path("id")id:Int): Call<ResultAluno>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @PUT("/v1/sinalibras/aluno/perfil/{id}")
    fun setAtualizarAlunoSenha(@Path("id")id:Int,@Body usuario: Aluno): Call<ResultAluno>




    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/usuario")
    fun setSalvarUsuarioTemporario(@Body usuario: Professor): Call<ResultUsuarioTeste>

    @GET("/v1/sinalibras/usuario/email/{email}")
    fun getUsuarioEmail(@Path("email")email:String): Call<ResultUsuarioTeste>





    @Headers("Accept: application/json")
    @GET("/v1/sinalibras/alunos")
    fun getAllProfessores(): Call<ResultProfessores>

    @GET("/v1/sinalibras/professor/{id}")
    fun getProfessorId(@Path("id")id:Int): Call<ResultProfessor>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/professor")
    fun setSalvarProfessor(@Body usuario: Professor): Call<ResultProfessor>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @PUT("/v1/sinalibras/professor/{id}")
    fun setAtualizarProfessor(@Path("id")id:Int,@Body usuario: Professor): Call<ResultProfessor>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @POST("/v1/sinalibras/professor/validacao")
    fun setValidarEntradaProfessor(@Body usuario: Professor): Call<ResultProfessor>

    @DELETE("/v1/sinalibras/professor/perfil/{id}")
    fun setDellProfessor(@Path("id")id:Int): Call<ResultProfessor>

    @Headers("Content-Type: application/json")//anotacao pra q o content type desse treco seja json
    @PUT("/v1/sinalibras/professor/perfil/{id}")
    fun setAtualizarProfessorSenha(@Path("id")id:Int,@Body usuario: Aluno): Call<ResultProfessor>


}
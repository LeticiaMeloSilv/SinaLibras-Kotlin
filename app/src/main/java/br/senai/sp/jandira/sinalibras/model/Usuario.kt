package br.senai.sp.jandira.sinalibras.model

data class Usuario(
    val id_aluno:Long=0,
    val nome:String="",
    val data_cadastro:String="",
    val email:String="",
    val senha:String="",
    val data_nascimento:String="",
    val foto_perfil:String=""
)

data class AlunoResponse(
    val id_aluno: Int,
    val status: String,
    val message: String
)
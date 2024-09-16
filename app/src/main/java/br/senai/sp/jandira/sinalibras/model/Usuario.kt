package br.senai.sp.jandira.sinalibras.model

data class Usuario(
    val id:Long=0,
    val nome:String="",
    val data_cadastro:String="",
    val email:String="",
    val senha:String="",
    val data_nascimento:String="",
    val foto_perfil:String=""
)

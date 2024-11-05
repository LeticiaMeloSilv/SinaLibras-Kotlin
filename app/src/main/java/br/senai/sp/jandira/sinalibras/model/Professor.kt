package br.senai.sp.jandira.sinalibras.model

data class Professor(
    val id_professor:Long=0,
    val nome:String="",
    val data:String="",
    val email:String="",
    val senha:String="",
    val data_nascimento:String="",
    val foto_perfil:String=""
)

package br.senai.sp.jandira.sinalibras.model

data class Aluno(
    val id_aluno:Long=0,
    val nome:String="",
    val data_cadastro:String="",
    val email:String="",
    val senha:String="",
    val data_nascimento:String="",
    val foto_perfil:String="https://firebasestorage.googleapis.com/v0/b/sinalibras-439801.appspot.com/o/perfil.png?alt=media&token=389f67ce-ff31-4bbd-b1db-c014e30448c1"
)

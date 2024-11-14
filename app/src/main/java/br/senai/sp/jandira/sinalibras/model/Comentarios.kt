package br.senai.sp.jandira.sinalibras.model

data class Comentarios(
    val id_comentario:Long=0,
    val comentario:String="",
    val data:String="",
    val id_videoaula:Long=0,
    val id_aluno:Long=0,
)

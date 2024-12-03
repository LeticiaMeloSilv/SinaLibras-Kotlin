package br.senai.sp.jandira.sinalibras.model

data class Postagem(
    val id_postagem:Long=0,
    val id_professor:Long=0,

    val texto:String="",
    val data:String="",
    val foto_postagem:String="",
    val comentarios:List<Comentarios>?=null,
    val professor:List<Professor>?=null,
    )

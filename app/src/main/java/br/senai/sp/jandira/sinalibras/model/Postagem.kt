package br.senai.sp.jandira.sinalibras.model

data class Postagem(
    val id_postagem:Long=0,
    val texto:String="",
    val data:String="",
    val id_professor:Long=0,
    val foto_postagem:String=""
    )

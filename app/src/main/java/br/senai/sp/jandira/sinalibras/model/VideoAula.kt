package br.senai.sp.jandira.sinalibras.model

data class VideoAula(
    val id_video:Long=0,
    val url_video:String="",
    val titulo:String="",
    val duracao:String="",
    val data_postagem:String="",
    val foto_capa:String="",
    val id_modulo:String="",
    val id_professor:String=""
)

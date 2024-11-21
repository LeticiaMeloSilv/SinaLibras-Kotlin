package br.senai.sp.jandira.sinalibras.model

data class VideoAula(
    val id_videoaula:Long=0,
    val url_video:String="",
    val titulo:String="",
    val duracao:String="",
    val data_cadastro:String="",
    val foto_capa:String="",
    val modulo:List<Modulo>?=null,
    val nivel:List<Nivel>?=null,
    val comentarios:List<Comentarios>?=null,
    val professor:List<Professor>?=null,
    val id_professor:Long=0,
    val id_modulo: Int =0,
    val id_nivel: Int =0,
    )

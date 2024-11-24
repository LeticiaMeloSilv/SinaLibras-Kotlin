package br.senai.sp.jandira.sinalibras.model

data class Feed(
    val id:Long=0,
    val conteudo:String="",
    val foto:String="",
    val descricao:String="",
    val duracao:String="",
    val data:String="",
    val tipo:String="",
    val titulo:String="",
    val foto_capa:String="",
    val url_video:String="",
    val modulo:Modulo?=null,
    val nivel:Nivel?=null,
    val comentarios:List<Comentarios>?=null,
    val professor:Professor?=null,
    )

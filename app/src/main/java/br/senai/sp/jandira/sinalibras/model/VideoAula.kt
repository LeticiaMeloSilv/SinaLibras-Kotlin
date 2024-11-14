package br.senai.sp.jandira.sinalibras.model

data class VideoAula(
    val id_videoaula:Long=0,
    val url_video:String="",
    val titulo:String="",
    val duracao:String="",
    val data_cadastro:String="",
    val foto_capa:String="",
    val modulo:List<Modulo>,
    val nivel:List<Nivel>,
    val comentarios:List<Comentarios>,
    val professor:List<Professor>
)

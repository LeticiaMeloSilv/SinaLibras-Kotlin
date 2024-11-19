package br.senai.sp.jandira.sinalibras.model

data class ResultComentario(
    val comentario: Comentarios?=null,
    val status: Boolean,
    val status_code: Int,
    val message: String
)
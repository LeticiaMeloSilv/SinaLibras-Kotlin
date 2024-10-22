package br.senai.sp.jandira.sinalibras.model


data class ResultVideo(
    val video: VideoAula?=null,
    val status: Boolean,
    val status_code: Int,
    val message: String
)

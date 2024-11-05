package br.senai.sp.jandira.sinalibras.model


data class ResultVideo(
    val video: List<VideoAula>?=null,
    val status: Boolean?=null,
    val status_code: Int?=null,
    val message: String?=null
)

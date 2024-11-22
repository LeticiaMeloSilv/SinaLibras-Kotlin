package br.senai.sp.jandira.sinalibras.model


data class ResultPostagem(
    val postagem: Postagem?=null,
    val status: Boolean?=null,
    val status_code: Int?=null,
    val message: String?=null
)

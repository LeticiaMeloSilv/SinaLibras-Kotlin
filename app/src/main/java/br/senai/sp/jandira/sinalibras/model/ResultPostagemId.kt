package br.senai.sp.jandira.sinalibras.model


data class ResultPostagemId(
    val postagem: List<Postagem>?=null,
    val status: Boolean?=null,
    val status_code: Int?=null,
    val message: String?=null
)

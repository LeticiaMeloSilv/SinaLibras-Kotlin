package br.senai.sp.jandira.sinalibras.model

data class ResultModulo(
    val modulo: List<Modulo>?=null,
    val status: Boolean?=null,
    val status_code: Int?=null,
    val message: String?=null
)

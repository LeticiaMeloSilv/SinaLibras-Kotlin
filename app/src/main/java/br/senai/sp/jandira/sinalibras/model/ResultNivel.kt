package br.senai.sp.jandira.sinalibras.model

data class ResultNivel(
    val niveis: List<Nivel>?=null,
    val status: Boolean?=null,
    val status_code: Int?=null,
    val message: String?=null
)

package br.senai.sp.jandira.sinalibras.model

data class ResultProfessores(
    val professor: List<Professor>?=null,
    val status: Boolean=false,
    val status_code: Int=0,
    val message: String="",
    val quantidade: Int = 0,
)
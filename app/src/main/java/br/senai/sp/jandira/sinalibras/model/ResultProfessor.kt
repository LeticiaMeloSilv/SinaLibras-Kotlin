package br.senai.sp.jandira.sinalibras.model

data class ResultProfessor(
    val professor: Professor?=null,
    val status: Boolean,
    val status_code: Int,
    val message: String
)
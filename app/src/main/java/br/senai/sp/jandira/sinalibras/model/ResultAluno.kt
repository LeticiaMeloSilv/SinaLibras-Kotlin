package br.senai.sp.jandira.sinalibras.model

data class ResultAluno(
    val aluno: Aluno?=null,
    val status: Boolean,
    val status_code: Int,
    val message: String
)
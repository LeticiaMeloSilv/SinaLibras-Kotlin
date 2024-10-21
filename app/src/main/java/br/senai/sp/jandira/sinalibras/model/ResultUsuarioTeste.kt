package br.senai.sp.jandira.sinalibras.model

data class ResultUsuarioTeste(
    val usuario: UsuarioTeste,
    val status: Boolean,
    val status_code: Int,
    val message: String
)
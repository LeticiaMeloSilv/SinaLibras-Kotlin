package br.senai.sp.jandira.sinalibras.model

data class ResultRespostaUsuario(
    val respostas: List<RespostaUsuario>,
    val status: Boolean,
    val status_code: Int,
    val message: String
)

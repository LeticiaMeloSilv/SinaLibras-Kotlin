package br.senai.sp.jandira.sinalibras.model

data class ResultQuiz(
    val questoes: List<Quiz>?=null,
    val status_code: Int? = null,
    val status: Boolean?=null,
    val message: String?=null

)

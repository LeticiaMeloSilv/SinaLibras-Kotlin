package br.senai.sp.jandira.sinalibras.model

data class Quiz(
    val id_pergunta: Int= 0,
    val pergunta: String= "",
    val video: String= "",
    val alternativas: List<Alternativas>?=null
)
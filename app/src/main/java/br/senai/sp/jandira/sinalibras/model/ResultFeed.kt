package br.senai.sp.jandira.sinalibras.model

data class ResultFeed(
    val feed: List<Feed>?=null,
    val status: Boolean?=null,
    val status_code: Int?=null,
    val message: String?=null
)

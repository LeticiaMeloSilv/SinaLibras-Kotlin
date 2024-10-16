package br.senai.sp.jandira.sinalibras.model

data class Email(
    val credits:Long=0,
    val result:String="",
    val details:String="",
    val free:Boolean=false,
    val role:Boolean=false,
    val disposable:Boolean=false,
    val ok_for_all:Boolean=false,
    val protected:Boolean=false,
    val did_you_mean:String?="",
    val email:String="",
    val email_normalized:String="",
    val success:Boolean=false,
    val message:Boolean?=null
)
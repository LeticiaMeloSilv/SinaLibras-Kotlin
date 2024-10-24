package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun EditarPerfil(controleDeNavegacao: NavHostController, recebido: String) {
    val partes = recebido.split("*")
    Log.i("USUARIO", recebido)
    val dadosUsuario = partes[0]
    val tipoUsuario = partes[1]
    Log.i("USUARIO", dadosUsuario)

//
//    val dataNascimento = dadosUsuario.data_nascimento.take(10)
//    val data = dataNascimento.split("-")
//    val dia = data[2]
//    val mes = data[1]
//    val ano = data[0]
//    val dataFormatada = "$dia/$mes/$ano"
//
//
//    var nomeState = remember {
//        mutableStateOf(dadosUsuario.nome)
//    }
//    var emailState = remember {
//        mutableStateOf(dadosUsuario.email)
//    }
//    var nascimentoState = remember {
//        mutableStateOf(dataFormatada)
//    }
//    var fotoState = remember {
//        mutableStateOf(dadosUsuario.foto)
//    }
//    var umError = remember {
//        mutableStateOf(false)
//    }
//    var mensagemErroState = remember {
//        mutableStateOf("")
//    }
//
//    val painter: Painter =
//        if (dadosUsuario.foto_perfil != null && dadosUsuario.foto_perfil != "null" && dadosUsuario.foto_perfil.isNotEmpty()) {
//            rememberAsyncImagePainter(model = dadosUsuario.foto_perfil)
//        } else {
//            painterResource(id = R.drawable.perfil)
//        }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFC7E2FE))
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(45.dp)
//
//        ) {
//
//            Button(
//                onClick = {
//                    controleDeNavegacao.navigate("perfil/${recebido}")
//                },
//                colors = ButtonColors(
//                    Color.Transparent,
//                    Color.Transparent,
//                    Color.Transparent,
//                    Color.Transparent
//                )
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.btn_voltar),
//                    contentDescription = "Botao Voltar",
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = "Editar Perfil",
//                fontWeight = FontWeight.Bold,
//                fontSize = 22.sp,
//                color = Color.Black,
//                modifier = Modifier
//                    .padding(top = 10.dp, bottom = 10.dp)
//                    .align(Alignment.TopCenter)
//            )
//        }
//
//
//
//        Box(
//            modifier = Modifier
//                .size(100.dp)
//                .background(Color(0xFF58B86E), shape = CircleShape),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//
//                painter = painter,
//                contentDescription = "foto de Perfil",
//                modifier = Modifier
//                    .size(width = 170.dp, height = 170.dp)
//            )
//            Box(
//                modifier = Modifier
//                    .size(30.dp)
//                    .background(Color(0xFF3F51B5), shape = CircleShape)
//                    .align(Alignment.BottomEnd)
//            ) {
//                Icon(
//                    Icons.Default.Edit,
//                    contentDescription = "Editar",
//                    tint = Color.White,
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        OutlinedTextField(
//            value = nomeState.value,
//            onValueChange = {nomeState.value = it},
//            label = { Text("Nome") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//        )
//
//        OutlinedTextField(
//            value = nascimentoState.value,
//            onValueChange = {nascimentoState.value = it},
//            label = { Text("Data de Nascimento") },
//            trailingIcon = {
//                Icon(Icons.Default.DateRange, contentDescription = "Selecionar data")
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//        )
//
//        OutlinedTextField(
//            value = emailState.value,
//            onValueChange = {emailState.value = it},
//            label = { Text("Email") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 8.dp)
//        )
//
//        Card(
//            modifier = Modifier
//                .border(
//                    width = 2.dp,
//                    color = Color(0xFF65558F),
//                    shape = RoundedCornerShape(16.dp)
//                )
//                .background(Color.Transparent)
//                .fillMaxWidth()
//                .padding(vertical = 8.dp)
//                .clickable {controleDeNavegacao.navigate("editarSenha/${recebido}") },
//            shape = RoundedCornerShape(12.dp),
//
//
//            ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(text = "Alterar Senha", fontSize = 16.sp, fontWeight = FontWeight.Medium)
//                Icon(Icons.Default.ArrowForward, contentDescription = "Alterar Senha")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(224.dp))
//
//
//        Button(
//            onClick = {
//                if(tipoUsuario=="aluno"){
//                val callAtualizarAluno = RetrofitFactory().getUsuarioService().setAtualizarAluno(dadosUsuario.id,
//                    usuario = Aluno(
//                    nome = nomeState.value,
//                    email = emailState.value.lowercase(),
//                    data_nascimento = nascimentoState.value,
//                    foto_perfil = fotoState.value,
//                )
//                )
//                callAtualizarAluno.enqueue(object : Callback<ResultAluno> {
//                    override fun onResponse(p0: Call<ResultAluno>, p1: Response<ResultAluno>) {
//                        val alunoResponse = p1.body()
//                        if (p1.isSuccessful) {
//                            if (alunoResponse != null) {
//                                controleDeNavegacao.navigate("configuracoes/${alunoResponse.aluno}*aluno")
//                            }
//
//                        } else {
//                            Log.i("CALMA", alunoResponse?.message!!.toString())
//                        }
//                    }
//
//                    override fun onFailure(p0: Call<ResultAluno>, p1: Throwable) {
//                        Log.i("ERRO_EDITAR_PERFIL", p1.toString())
//                    }
//                })
//                }
//                else{
//                    val callAtualizarProfessor = RetrofitFactory().getUsuarioService().setAtualizarProfessor(dadosUsuario.id,
//                        usuario = Professor(
//                            nome = nomeState.value,
//                            email = emailState.value.lowercase(),
//                            data_nascimento = nascimentoState.value,
//                            foto_perfil = fotoState.value,
//                        )
//                    )
//                    callAtualizarProfessor.enqueue(object : Callback<ResultProfessor> {
//                        override fun onResponse(p0: Call<ResultProfessor>, p1: Response<ResultProfessor>) {
//                            val professorResponse = p1.body()
//                            if (p1.isSuccessful) {
//                                if (professorResponse != null) {
//                                    controleDeNavegacao.navigate("configuracoes/${professorResponse.professor}*professor")
//                                }
//
//                            } else {
//                                Log.i("CALMA", professorResponse?.message!!.toString())
//                            }
//                        }
//
//                        override fun onFailure(p0: Call<ResultProfessor>, p1: Throwable) {
//                            Log.i("ERRO_EDITAR_PERFIL", p1.toString())
//                        }
//                    })
//                }
//            },
//            modifier = Modifier
//                .width(412.dp)
//                .height(56.dp)
//                .padding(horizontal = 32.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
//            shape = RoundedCornerShape(10.dp)
//        ) {
//            Text(
//                text = "Salvar Alterações",
//                fontSize = 20.sp,
//                color = Color.White,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }

}
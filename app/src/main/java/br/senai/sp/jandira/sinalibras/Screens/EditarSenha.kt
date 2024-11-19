package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarSenha(
    controleDeNavegacao: NavHostController,
    id: String,
    email: String,
    fotoPerfil: String,
    tipoUsuario: String,nome: String, dataNascimento: String
) {

    var emailState=remember {
        mutableStateOf("")
    }
    var emailErrado=remember{
        mutableStateOf(true)
    }
    var senhaState = remember {
        mutableStateOf("")
    }
    var confirmaSenhaState= remember {
        mutableStateOf("")
    }
    var mensagemErroState = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                controleDeNavegacao.navigate("editarPerfil?id=${id}&email=${email}&nome=${nome}&dataNascimento=${dataNascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
            },
            colors = ButtonColors(
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
                Color.Transparent
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.btn_voltar),
                contentDescription = "Botao Voltar",
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = "Alterar Senha",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        )

        Box(
            modifier = Modifier
                .background(Color(0xFF485F9A), shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Escreva seu email no campo abaixo para confirmar a ação",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = emailState.value,
                        onValueChange = {emailState.value=it},
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = {
                        if(emailState.value==email){
                            emailErrado.value=true
                        }
                    }) {
                        Icon(Icons.Default.Send , contentDescription = "Enviar Código", tint = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = mensagemErroState.value,
            color = Color.Red,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = senhaState.value,
            onValueChange = {senhaState.value=it},
            label = { Text("Nova Senha", color = Color(0xFF485F9A)) },
            enabled = emailErrado.value==false,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF485F9A),
                unfocusedIndicatorColor = Color(0xFF485F9A),
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = confirmaSenhaState.value,
            onValueChange = {confirmaSenhaState.value=it},
            label = { Text("Confirmar Senha", color = Color(0xFF485F9A)) },
            enabled = emailErrado.value==false,
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { /* Ação exibir senha */ }) {
                    Icon(Icons.Default.VisibilityOff, contentDescription = null, tint = Color(0xFF485F9A))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF485F9A),
                unfocusedIndicatorColor = Color(0xFF485F9A),
            )
        )

        Spacer(modifier = Modifier.height(134.dp))

        Button(
            onClick = {
                if (senhaState.value == "" || confirmaSenhaState.value == "") {
                    mensagemErroState.value = "Todos os campos devem ser preenchidos"
                } else if (senhaState.value != confirmaSenhaState.value) {
                    mensagemErroState.value = "Sua senha não confere"
                } else if (senhaState.value.length > 8 || confirmaSenhaState.value.length > 8) {
                    mensagemErroState.value = "Sua senha deve ter 8 caracteres"
                }
                else{
                    if(tipoUsuario=="aluno"){
                        val callAtualizarAluno =
                            RetrofitFactory().getUsuarioService().setAtualizarAlunoSenha(
                                id.toInt(),
                                usuario = Aluno(
                                    senha = senhaState.value,
                                )
                            )
                        callAtualizarAluno.enqueue(object : Callback<ResultAluno> {
                            override fun onResponse(
                                p0: Call<ResultAluno>,
                                p1: Response<ResultAluno>
                            ) {
                                val alunoResponse = p1.body()
                                Log.i("ALUNO", alunoResponse.toString())
                                if (p1.isSuccessful) {
                                    if (alunoResponse != null) {
                                        controleDeNavegacao.navigate(
                                            "configuracoes?id=${alunoResponse.aluno?.id_aluno}&email=${alunoResponse.aluno?.email}&nome=${alunoResponse.aluno?.nome}&dataNascimento=${alunoResponse.aluno?.data_nascimento}&fotoPerfil=${alunoResponse.aluno?.foto_perfil}&tipoUsuario=${tipoUsuario}"
                                        )
                                    }

                                } else {
                                    Log.i("CALMA", alunoResponse?.message!!.toString())
                                }
                            }

                            override fun onFailure(p0: Call<ResultAluno>, p1: Throwable) {
                                Log.i("ERRO_EDITAR_PERFIL", p1.toString())
                            }
                        })

                }
                    else{
                            val callAtualizarProfessor =
                                RetrofitFactory().getUsuarioService().setAtualizarProfessorSenha(
                                    id.toInt(),
                                    usuario = Aluno(
                                        senha = senhaState.value,
                                    )
                                )
                        callAtualizarProfessor.enqueue(object : Callback<ResultProfessor> {
                                override fun onResponse(
                                    p0: Call<ResultProfessor>,
                                    p1: Response<ResultProfessor>
                                ) {
                                    val alunoResponse = p1.body()
                                    Log.i("Professor", alunoResponse.toString())
                                    if (p1.isSuccessful) {
                                        if (alunoResponse != null) {
                                            controleDeNavegacao.navigate(
                                                "configuracoes?id=${alunoResponse.professor?.id_professor}&email=${alunoResponse.professor?.email}&nome=${alunoResponse.professor?.nome}&dataNascimento=${alunoResponse.professor?.data_nascimento}&fotoPerfil=${alunoResponse.professor?.foto_perfil}&tipoUsuario=${tipoUsuario}"
                                            )
                                        }

                                    } else {
                                        Log.i("CALMA", alunoResponse?.message!!.toString())
                                    }
                                }

                                override fun onFailure(p0: Call<ResultProfessor>, p1: Throwable) {
                                    Log.i("ERRO_EDITAR_PERFIL", p1.toString())
                                }
                            })


                    }
                }
                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF485F9A))
        ) {
            Text(text = "Confirmar", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "obs: caso não o encontre na caixa de entrada, verifique a caixa Spam",
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
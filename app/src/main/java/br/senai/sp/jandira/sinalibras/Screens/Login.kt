package br.senai.sp.jandira.sinalibras.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.sinalibras.R
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Login(controleDeNavegacao: NavHostController) {
    var emailState = remember {
        mutableStateOf("")
    }
    var senhaState = remember {
        mutableStateOf("")
    }
    //mudar visibilidade da senha
    var senhaVisivel by remember { mutableStateOf(false) }
    val textoOculto =
        if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation()
    var umError = remember {
        mutableStateOf(false)
    }
    var mensagemErroState = remember {
        mutableStateOf("")
    }
    //gradiente do background
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFC7E2FE), Color(0xFF345ADE)),
        start = Offset.Zero,
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )




    Column(
        modifier = Modifier
            .background(brush = gradientBrush)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Button(
            onClick = {
                controleDeNavegacao.navigate("inicio")
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
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logotipo da aplicacao",
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Acesse a sua conta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff3459DE)
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Column(
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            TextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth(),
                isError = umError.value,
                colors = TextFieldDefaults
                    .colors(
                        focusedContainerColor = Color.Transparent,
                        focusedTextColor = Color(0xff334EAC),
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedTextColor = Color(0xff334EAC),
                        errorTextColor = Color(0xff334EAC),
                        errorContainerColor = Color.Transparent,
                        errorPlaceholderColor = Color(0xff334EAC),
                        errorLabelColor = Color(0xff334EAC),
                        focusedPlaceholderColor = Color(0xff334EAC),
                        unfocusedPlaceholderColor = Color(0xff334EAC),
                        unfocusedLabelColor = Color(0xff334EAC),
                        focusedLabelColor = Color(0xff334EAC)
                    )
            )
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = senhaState.value,
                onValueChange = {
                    senhaState.value = it
                },
                label = { Text("Senha") },
                isError = umError.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = textoOculto,
                trailingIcon = {
                    val image = if (senhaVisivel) {
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    }

                    IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                        Icon(imageVector = image, "", tint = Color(0xff334EAC))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults
                    .colors(
                        focusedContainerColor = Color.Transparent,
                        focusedTextColor = Color(0xff334EAC),
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedTextColor = Color(0xff334EAC),
                        errorTextColor = Color(0xff334EAC),
                        errorContainerColor = Color.Transparent,
                        errorPlaceholderColor = Color(0xff334EAC),
                        errorLabelColor = Color(0xff334EAC),
                        focusedPlaceholderColor = Color(0xff334EAC),
                        unfocusedPlaceholderColor = Color(0xff334EAC),
                        unfocusedLabelColor = Color(0xff334EAC),
                        focusedLabelColor = Color(0xff334EAC)
                    )
            )
        }
        Text(
            text = mensagemErroState.value,
            color = Color.Red,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                if (emailState.value == "" || senhaState.value == "") {
                    mensagemErroState.value = "Todos os campos devem ser preenchidos"
                    umError.value = true
                }
                val callAluno = RetrofitFactory()
                    .getUsuarioService().setValidarEntradaAluno(
                        usuario = Aluno(
                            email = emailState.value,
                            senha = senhaState.value
                        )
                    )
                callAluno.enqueue(object : Callback<ResultAluno> {
                    override fun onResponse(
                        p0: Call<ResultAluno>,
                        p1: Response<ResultAluno>
                    ) {
                        val alunoList = p1.body()
                        Log.i("CARA",p0.toString())
                        Log.i("CARA",alunoList.toString())
                        if(p1.isSuccessful){
                            if (alunoList != null) {
                                controleDeNavegacao.navigate("feed?id=${alunoList.aluno?.id_aluno}&tipoUsuario=aluno&fotoPerfil=${alunoList.aluno?.foto_perfil}")
                            }
                        }
                        else{
                            val callProfessor = RetrofitFactory()
                                .getUsuarioService().setValidarEntradaProfessor(
                                    usuario = Professor(
                                        email = emailState.value,
                                        senha = senhaState.value
                                    )
                                )
                            callProfessor.enqueue(object : Callback<ResultProfessor> {
                                override fun onResponse(p0: Call<ResultProfessor>, p1: Response<ResultProfessor>) {
                                    val professorList = p1.body()
                                    Log.i("CARA",p0.toString())
                                    Log.i("CARA",professorList.toString())
                                    if(p1.isSuccessful){
                                        if (professorList != null) {
                                            controleDeNavegacao.navigate("feed?id=${professorList.professor?.id_professor}&tipoUsuario=professor&fotoPerfil=${professorList.professor?.foto_perfil}")
                                        }
                                    }
                                    else{
                                        val errorBody = p1.errorBody()?.string()
                                        val gson = Gson()
                                        val usuarioSalvo = gson.fromJson(errorBody, ResultAluno::class.java)
                                        mensagemErroState.value = usuarioSalvo.message
                                        umError.value = true

                                    }
                                                                }

                                override fun onFailure(p0: Call<ResultProfessor>, p1: Throwable) {
                                    Log.i("ERRO_LOGIN", p1.toString())
                                    mensagemErroState.value =
                                        "Ocorreu um erro, o serviço pode estar indisponivel. Favor, verifique se está conectado a internet ou tente novamente mais tarde"}

                            })
 }


                    }

                    override fun onFailure(p0: Call<ResultAluno>, p1: Throwable) {
                        Log.i("ERRO_LOGIN", p1.toString())
                        mensagemErroState.value =
                            "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"

                    }

                })
            },
            colors = ButtonColors(
                containerColor = Color(0xffEFF3FF),
                contentColor = Color(0xffEFF3FF),
                disabledContentColor = Color(0xffEFF3FF),
                disabledContainerColor = Color(0xffEFF3FF)
            ),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(40.dp, 18.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)

        ) {
            Text(
                text = "ENTRAR",
                color = Color(0xff345ADE),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 28.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Esqueci a senha",
            modifier = Modifier
                .clickable { controleDeNavegacao.navigate("inicio") }
                .align(Alignment.End)
                .padding(end = 32.dp),
            color = Color(0xffEFF3FF),
            fontWeight = FontWeight.Bold
        )
    }
}
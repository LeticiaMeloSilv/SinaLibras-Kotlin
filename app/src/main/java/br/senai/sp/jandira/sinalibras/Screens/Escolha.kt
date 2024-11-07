
package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.Email
import br.senai.sp.jandira.sinalibras.model.ResultUsuarioTeste
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.ChronoUnit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Escolha(controleDeNavegacao: NavHostController) {

    var emailState = remember {
        mutableStateOf("")
    }
    var umError = remember {
        mutableStateOf(false)
    }
    var mensagemErroState = remember {
        mutableStateOf("")
    }
    var focusTela = remember {
        mutableStateOf(
            false
        )
    }

    var emailValido by remember {
        mutableStateOf(Email())
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
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(32.dp))
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
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Escolha como você pretende participar",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color(0xFF345ADE),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(20.dp)

            )
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier
                    .clickable {
                        if (focusTela.value) {

                        } else {
                            focusTela.value = true
                        }
                    }
                    .border(
                        width = 4.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(200.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x66FFFFFF))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.professor),
                        contentDescription = "Participar como Professor",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "Professor",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF345ADE),
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier
                    .clickable {
                        if (focusTela.value) {

                        } else {
                            controleDeNavegacao.navigate("cadastro/")
                        }
                    }
                    .border(
                        width = 4.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(200.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x66FFFFFF))

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.aluno),
                        contentDescription = "Participar como Aluno",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        text = "Aluno",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF345ADE),
                    )
                }
            }
        }
    }
    if (focusTela.value) {
        Column(
            modifier = Modifier
                .offset(x = 0.dp, y = 0.dp)
                .background(color = Color(0x663A3D46))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xffffffff))
                    .size(height = 600.dp, width = 300.dp)
                    .padding(10.dp)
                    .border(
                        width = 0.dp,
                        shape = RoundedCornerShape(size = 2.dp),
                        color = Color.Transparent
                    )
            ) {
                Button(
                    onClick = {
                        focusTela.value = false
                        emailState.value = ""
                        mensagemErroState.value = ""
                    },
                    colors = ButtonColors(
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent,
                        Color.Transparent
                    ),
                    modifier = Modifier.offset(x = -20.dp, y = -10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.btn_cancelar),
                        contentDescription = "Botao cancelar ação",
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(
                    text = "Para ser mentor em nosso aplicativo, precisamos validar seu nivel de conhecimento em Libras, para isso você precisará responder a 15 perguntas.",
                    fontSize = 16.sp,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "É necessário ter 70% de acerto, ou mais!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Mas antes, escreva seu endereço de email no campo a baixo",
                    fontSize = 15.sp,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = emailState.value,
                    onValueChange = {
                        emailState.value = it
                    },
                    label = { Text("Email") },
                    isError = umError.value,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults
                        .colors(
                            focusedContainerColor = Color.Transparent,
                            focusedTextColor = Color(0xff22367C),
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedTextColor = Color(0xff22367C),
                            errorTextColor = Color(0xff22367C),
                            errorContainerColor = Color.Transparent,
                            errorLabelColor = Color(0xff22367C),
                            unfocusedLabelColor = Color(0xff22367C),
                            focusedLabelColor = Color(0xff22367C)
                        )
                )
                Text(
                    text = mensagemErroState.value,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        if (emailState.value == "") {
                            mensagemErroState.value = "Preencha este campo primeiro"
                        } else {
//                            val callEmail = RetrofitFactory()
//                                .getEmailValidoService().getEmailValido(emailState.value)
//
//                            callEmail.enqueue(object : Callback<Email> {
//                                override fun onResponse(p0: Call<Email>, p1: Response<Email>) {
//                                    emailValido=p1.body()!!
//                                    if (emailValido.credits.toString()=="0"){
//                                        Log.i("EMAIL_CREDITS", emailValido.credits.toString())
//                                        mensagemErroState.value="Ocorreu um erro por parte do servidor, favor constatar a equipe"
//                                    }
//                                    else if(emailValido.result=="invalid"){
//                                        mensagemErroState.value="Endereço de email invalido, verifique se preencheu corretamente"
//                                    }
//                                    else if (emailValido.result=="valid"){
//                                        Log.i( "DEU",emailValido.email)
                            val currentDate: LocalDate = LocalDate.now()
                            val email = emailState.value.lowercase()
                            val callUsuarioByEmail =
                                RetrofitFactory().getUsuarioService().getUsuarioEmail(email)
                            callUsuarioByEmail.enqueue(object : Callback<ResultUsuarioTeste> {
                                override fun onResponse(
                                    p0: Call<ResultUsuarioTeste>,
                                    p1: Response<ResultUsuarioTeste>
                                ) {
                                    val dadoUsuario = p1.body()
                                    if (p1.isSuccessful) {
                                        if (dadoUsuario != null) {

                                            if (dadoUsuario.status_code == 200) {
                                                val dataUsuarioCadastrado =
                                                    dadoUsuario.usuario.data_cadastro.take(10)
                                                val data = dataUsuarioCadastrado.split("-")
                                                val dia = data[2]
                                                val mes = data[1]
                                                val ano = data[0]
                                                val dataInicial = LocalDate.of(
                                                    ano.toInt(), mes.toInt(),
                                                    dia.toInt()
                                                )
                                                val tempo = ChronoUnit.DAYS.between(
                                                    dataInicial,
                                                    currentDate
                                                            )
                                                val tempoRestante=30-tempo.toInt()
                                                Log.i("data",tempoRestante.toString())

                                                if (tempo.toInt() > 30) {
                                                    val callUsuarios = RetrofitFactory()
                                                        .getUsuarioService()
                                                        .setSalvarUsuarioTemporario(
                                                            usuario = Professor(
                                                                email = email,
                                                                data_cadastro = currentDate.toString()
                                                            )
                                                        )

                                                    callUsuarios.enqueue(object :
                                                        Callback<ResultUsuarioTeste> {
                                                        override fun onResponse(
                                                            p0: Call<ResultUsuarioTeste>,
                                                            p1: Response<ResultUsuarioTeste>
                                                        ) {
                                                            val resposta = p1.body()
                                                            if (resposta == null) {
                                                                mensagemErroState.value =
                                                                    "Algo deu errado :(, favor verificar se os campos foram preenchidos corretamente"
                                                                umError.value = true

                                                            } else {
                                                                controleDeNavegacao.navigate(
                                                                    "quiz?idFornecido=${resposta.usuario.id_usuario_teste}&emailFornecido=${email}"
                                                                )
                                                            }
                                                        }

                                                        override fun onFailure(
                                                            p0: Call<ResultUsuarioTeste>,
                                                            p1: Throwable
                                                        ) {
                                                            Log.i(
                                                                "ERRO_CADASTRO",
                                                                p1.toString()
                                                            )
                                                            mensagemErroState.value =
                                                                "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"
                                                        }

                                                    })
                                                } else {
                                                    Log.i("data",tempoRestante.toString())
                                                    controleDeNavegacao.navigate("erro?porcentagem=${dadoUsuario.usuario.porcentagem}&tempoRestante=${tempoRestante.toString()}")
                                                }

                                            }
                                            else if (dadoUsuario.status_code == 404) {
                                                val callUsuarios = RetrofitFactory()
                                                    .getUsuarioService()
                                                    .setSalvarUsuarioTemporario(
                                                        usuario = Professor(
                                                            email = email,
                                                            data_cadastro = currentDate.toString()
                                                        )
                                                    )

                                                callUsuarios.enqueue(object :
                                                    Callback<ResultUsuarioTeste> {
                                                    override fun onResponse(
                                                        p0: Call<ResultUsuarioTeste>,
                                                        p1: Response<ResultUsuarioTeste>
                                                    ) {
                                                        val resposta = p1.body()
                                                        if (resposta == null) {

                                                            mensagemErroState.value =
                                                                "Algo deu errado :(, favor verificar se os campos foram preenchidos corretamente"
                                                            umError.value = true
                                                            Log.i("ERRO_CADASTRO", 5.toString())

                                                        } else {
                                                            controleDeNavegacao.navigate(
                                                                "quiz?idFornecido=${resposta.usuario.id_usuario_teste}&emailFornecido=${email}"
                                                            )
                                                        }
                                                    }

                                                    override fun onFailure(
                                                        p0: Call<ResultUsuarioTeste>,
                                                        p1: Throwable
                                                    ) {
                                                        Log.i(
                                                            "ERRO_CADASTRO",
                                                            p1.toString()
                                                        )
                                                        mensagemErroState.value =
                                                            "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"
                                                    }

                                                })
                                            } else {
//                                                val errorBody = p1.errorBody()?.string()
//                                                val gson = Gson()
//                                                val usuarioSalvo = gson.fromJson(
//                                                    errorBody,
//                                                    ResultAluno::class.java
//                                                )
                                                mensagemErroState.value = dadoUsuario.message
                                            }

                                        } else {
                                            mensagemErroState.value =
                                                "Ocorreu um erro, o serviço pode estar indisponivel. Favor, tente novamente mais tarde"
                                        }

                                    } else {
                                        mensagemErroState.value =
                                            "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"
                                    }
                                }

                                override fun onFailure(
                                    p0: Call<ResultUsuarioTeste>,
                                    p1: Throwable
                                ) {
                                    Log.i("ERRO_CADASTRO", p1.toString())

                                    mensagemErroState.value =
                                        "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"
                                }
                            })


//                                    }
//                                }
//
//                                override fun onFailure(p0: Call<Email>, p1: Throwable) {
//                                    mensagemErroState.value="Ocorreu um erro por parte do servidor, favor constatar a equipe"
//                                    Log.i("ERRO_EMAIL_ESCOLHA", p1.toString())
//
//                                }
//                            })
                        }
                    },
                    colors = ButtonColors(
                        containerColor = Color(0xff345ADE),
                        contentColor = Color(0xff345ADE),
                        disabledContentColor = Color(0xff345ADE),
                        disabledContainerColor = Color(0xff345ADE)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(40.dp, 18.dp),
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        text = "RESPONDER",
                        color = Color(0xffffffff),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 28.sp
                    )
                }
            }

        }
    }
}


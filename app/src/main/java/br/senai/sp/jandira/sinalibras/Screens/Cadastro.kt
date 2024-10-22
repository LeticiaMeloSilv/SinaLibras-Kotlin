package br.senai.sp.jandira.sinalibras

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.model.Email
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
//import java.time.LocalDate
import org.threeten.bp.LocalDate
import retrofit2.Response
import kotlin.text.subSequence

@Composable
fun Cadastro(controleDeNavegacao: NavHostController, emailProfessor: String) {

    var nomeState = remember {
        mutableStateOf("")
    }
    var emailValido by remember {
        mutableStateOf(Email())
    }
//pegando data atual
    val currentDate: LocalDate = LocalDate.now()

    var emailState = remember {
        mutableStateOf("")
    }
    if (emailProfessor !== "") {
        emailState.value = emailProfessor
    }
    var senhaState = remember {
        mutableStateOf("")
    }
    var confirmaSenhaState = remember {
        mutableStateOf("")
    }
    var nascimentoState = remember {
        mutableStateOf("")
    }
    var fotoState = remember {
        mutableStateOf("")
    }
    var umError = remember {
        mutableStateOf(false)
    }
    var mensagemErroState = remember {
        mutableStateOf("")
    }
//mudar visibilidade da senha
    var senhaVisivel by remember { mutableStateOf(false) }
    val textoOculto =
        if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation()
//mudar visibilidade da senha
    var confirmaSenhaVisivel by remember { mutableStateOf(false) }
    val textoOcultoConfirmaSenha =
        if (confirmaSenhaVisivel) VisualTransformation.None else PasswordVisualTransformation()
//bloquear caracteres especiais(pro nome, vou adicionai essa regra no data nascimento tbm, só q dai só autorizando numeros e /)
//    val filter = InputFilter { source, start, end, dest, dstart, dend ->
//        val regex = Regex("[^a-zA-Z0-9 ]")
//        if (regex.containsMatchIn(source.subSequence(start, end))) {
//            ""
//        } else {
//            null
//        }
//    }

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
        Spacer(modifier = Modifier.height(20.dp))
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
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logotipo da aplicacao",
                modifier = Modifier.size(75.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Criar Conta",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff3459DE)
            )

        }
        Spacer(modifier = Modifier.height(90.dp))
        Column(
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight()
        ) {
            TextField(
                value = nomeState.value,
                onValueChange = {
                    nomeState.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nome Completo") },
                isError = umError.value,
                //filters = arrayOf(filter),
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
            TextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth(),
                isError = umError.value,
                enabled = emailProfessor == "",
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
            TextField(
                value = confirmaSenhaState.value,
                onValueChange = {
                    confirmaSenhaState.value = it
                },
                label = { Text("Confirmar senha") },

                isError = umError.value,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = textoOcultoConfirmaSenha,
                trailingIcon = {
                    val image = if (confirmaSenhaVisivel) {
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    }

                    IconButton(onClick = { confirmaSenhaVisivel = !confirmaSenhaVisivel }) {
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
            TextField(
                value = nascimentoState.value,
                onValueChange = {
                    nascimentoState.value = it
                },
                label = { Text("Data de nascimento") },
                placeholder = { Text("ex: 00/00/0000") },
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
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = mensagemErroState.value, color = Color.Red)
            Spacer(modifier = Modifier.height(82.dp))
            Button(
                onClick = {
                    val callEmail = RetrofitFactory()
                        .getEmailValidoService().getEmailValido(emailState.value.lowercase())

                    callEmail.enqueue(object : Callback<Email> {
                        override fun onResponse(p0: Call<Email>, p1: Response<Email>) {
                            emailValido = p1.body()!!
//                            if (emailValido.credits.toString()=="0"){
//                                Log.i("EMAIL_CREDITS", emailValido.credits.toString())
//                                mensagemErroState.value="Ocorreu um erro por parte do servidor, favor constatar a equipe"
//                            }
//                            else if(emailValido.result=="invalid"){
//                                mensagemErroState.value="Endereço de email invalido, verifique se preencheu corretamente"
//                            }
//                            else if (emailValido.result=="valid"){
                            val nascimento = nascimentoState.value
                            var dataNascimento = ""
                            if (nascimento.contains("/")) {
                                //arruma o estilo da data pra mandar ela bonitinha pro back
                                val partes = nascimento.split("/")
                                val dia = partes[0]
                                val mes = partes[1]
                                val ano = partes[2]
                                dataNascimento = "$ano-$mes-$dia"
                                Log.i("323", dataNascimento)
                            } else {
                                mensagemErroState.value =
                                    "O formato do campo data de nascimento está incorreto"
                                umError.value = true
                            }
                            if (emailState.value == "" || nomeState.value == "" || nascimentoState.value == "" || senhaState.value == "" || confirmaSenhaState.value == "") {
                                mensagemErroState.value = "Todos os campos devem ser preenchidos"
                                umError.value = true
                            } else if (senhaState.value != confirmaSenhaState.value) {
                                mensagemErroState.value = "Sua senha não confere"
                                umError.value = true
                            } else if (senhaState.value.length > 8 || confirmaSenhaState.value.length > 8) {
                                mensagemErroState.value = "Sua senha deve ter 8 caracteres"
                                umError.value = true
                            } else if (nascimentoState.value.length > 10 || nascimentoState.value.length > 10) {
                                mensagemErroState.value =
                                    "O formato do campo data de nascimento está incorreto"
                                umError.value = true
                            } else {
                                if (emailProfessor == "") {
                                    val callUsuarios = RetrofitFactory()
                                        .getUsuarioService().setSalvarAluno(
                                            usuario = Aluno(
                                                nome = nomeState.value,
                                                email = emailState.value.lowercase(),
                                                senha = senhaState.value,
                                                data_nascimento = dataNascimento,
                                                foto_perfil = fotoState.value,
                                                data_cadastro = currentDate.toString()
                                            )
                                        )
                                    callUsuarios.enqueue(object : Callback<ResultAluno> {
                                        override fun onResponse(
                                            p0: Call<ResultAluno>,
                                            p1: Response<ResultAluno>
                                        ) {
                                            val usuarioSalvo = p1.body()

                                            if (p1.isSuccessful) {
                                                controleDeNavegacao.navigate("perfil/${usuarioSalvo?.aluno?.id_aluno}*aluno")

                                            } else {
                                                val errorBody = p1.errorBody()?.string()
                                                val gson = Gson()
                                                val usuarioSalvo = gson.fromJson(
                                                    errorBody,
                                                    ResultAluno::class.java
                                                )
                                                mensagemErroState.value = usuarioSalvo.message
                                                umError.value = true
                                            }
                                        }


                                        override fun onFailure(
                                            p0: Call<ResultAluno>,
                                            p1: Throwable
                                        ) {
                                            Log.i("ERRO_CADASTRO", p1.toString())
                                            mensagemErroState.value =
                                                "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"

                                        }

                                    })
                                }
                                else{
                                    val callUsuarios = RetrofitFactory()
                                        .getUsuarioService().setSalvarProfessor(
                                            usuario = Professor(
                                                nome = nomeState.value,
                                                email = emailState.value.lowercase(),
                                                senha = senhaState.value,
                                                data_nascimento = dataNascimento,
                                                foto_perfil = fotoState.value,
                                                data_cadastro = currentDate.toString()
                                            )
                                        )
                                    callUsuarios.enqueue(object : Callback<ResultProfessor> {
                                        override fun onResponse(
                                            p0: Call<ResultProfessor>,
                                            p1: Response<ResultProfessor>
                                        ) {
                                            val usuarioSalvo = p1.body()
                                            if (p1.isSuccessful) {
                                                controleDeNavegacao.navigate("perfil/${usuarioSalvo?.professor?.id_professor}*professor")
                                            }
                                            else {
                                                val errorBody = p1.errorBody()?.string()
                                                val gson = Gson()
                                                val usuarioSalvo = gson.fromJson(errorBody, ResultAluno::class.java)
                                                mensagemErroState.value = usuarioSalvo.message
                                                umError.value = true
                                            }
                                        }


                                        override fun onFailure(p0: Call<ResultProfessor>, p1: Throwable) {
                                            Log.i("ERRO_CADASTRO", p1.toString())
                                            mensagemErroState.value =
                                                "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"

                                        }

                                    })
                                }
                            }
                            //}
                        }

                        override fun onFailure(p0: Call<Email>, p1: Throwable) {
                            mensagemErroState.value =
                                "Ocorreu um erro por parte do servidor, favor constatar a equipe"
                            Log.i("ERRO_EMAIL_ESCOLHA", p1.toString())

                        }
                    })


                },
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = Color(0xffFFFFFF)
                    ),
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(size = 8.dp)
            ) {
                Text(
                    text = "CRIAR", color = Color(0xff3459DE),
                    fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
            }
        }

    }
}
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
import br.senai.sp.jandira.sinalibras.model.Usuario
import br.senai.sp.jandira.sinalibras.model.Result
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
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
        Text(text = mensagemErroState.value, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                if (emailState.value == "" || senhaState.value == "") {
                    mensagemErroState.value = "Todos os campos devem ser preenchidos"
                    umError.value = true
                }
                val callUsuarios = RetrofitFactory()
                    .getUsuarioService().validaEntrada(
                        usuario = Usuario(
                            email = emailState.value,
                            senha = senhaState.value
                        )
                    )
                callUsuarios.enqueue(object : Callback<Usuario> {
                    override fun onResponse(
                        p0: Call<Usuario>,
                        p1: retrofit2.Response<Usuario>
                    ) {
                        Log.i("RICKcerto", "onResponse:${p1}")

                        val alunoList = p1.body()
                        Log.i("RICKcerto", "onResponse:${p1.body()?.id_aluno} ")
                        Log.i("AAAAAAAAAAAAAAAAAAA", p1.errorBody().toString())
                        if(alunoList==null){
                            mensagemErroState.value =
                                "Algo deu errado :("
                            umError.value = true
                        }
                        else{
                                    controleDeNavegacao.navigate("perfil/${alunoList.id_aluno}")
                        }

                    }

                    override fun onFailure(p0: Call<Usuario>, p1: Throwable) {
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
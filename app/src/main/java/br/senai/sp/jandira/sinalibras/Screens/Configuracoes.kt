package br.senai.sp.jandira.sinalibras.Screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Configuracoes(
    controleDeNavegacao: NavHostController,
    id: String,
    email: String,
    nome: String,
    dataNascimento: String,
    fotoPerfil: String,
    tipoUsuario: String
) {
    var focusTela = remember {
        mutableStateOf(
            false
        )
    }
    var focusTelaSegundaConfirmacao = remember {
        mutableStateOf(
            false
        )
    }
    var emailState = remember {
        mutableStateOf("")
    }
    var umError = remember {
        mutableStateOf(false)
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


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}")
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
                text = "Configurações",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 60.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.Black)

            },
            label = { Text("Pesquisar...", color = Color.Black, fontWeight = FontWeight.Medium) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(315.dp)
                .height(44.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(13.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFACD0F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(13.dp)
        )
        Spacer(modifier = Modifier.height(80.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(301.dp)
                .height(436.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(76.dp)
                    .clickable {
                        controleDeNavegacao.navigate("editarPerfil?id=${id}&email=${email}&nome=${nome}&dataNascimento=${dataNascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                    },

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF4FF))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Meu Perfil",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Ir para Meu Perfil",
                        tint = Color.Black
                    )
                }
            }


            Card(
                modifier = Modifier
                    .padding(top = 86.dp)
                    .fillMaxWidth()
                    .height(76.dp)
                    .clickable {
                        controleDeNavegacao.navigate("sobreNos?id=${id}&email=${email}&nome=${nome}&dataNascimento=${dataNascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                    },

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF4FF))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Sobre Nós",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "ir para sobre nós",
                        tint = Color.Black
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(top = 172.dp)
                    .fillMaxWidth()
                    .height(76.dp)
                    .clickable {
                        controleDeNavegacao.navigate("suporte?id=${id}&email=${email}&nome=${nome}&dataNascimento=${dataNascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                    },

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF4FF))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Suporte/Denuncias",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Ir para suporte e denuncias",
                        tint = Color.Black
                    )
                }
            }


//            Card(
//                modifier = Modifier
//                    .padding(top = 258.dp)
//                    .fillMaxWidth()
//                    .height(76.dp),
//
//                shape = RoundedCornerShape(16.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF4FF))
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//
//                    Text(
//                        text = "Verificar Meu Nivel",
//                        fontWeight = FontWeight.Medium,
//                        fontSize = 18.sp,
//                        color = Color.Black,
//                        modifier = Modifier.weight(1f)
//                    )
//
//                    Icon(
//                        Icons.Default.ArrowForward,
//                        contentDescription = "Ir para Meu Perfil",
//                        tint = Color.Black
//                    )
//                }
//            }

            Card(
                modifier = Modifier
                    .padding(top = 344.dp)
                    .fillMaxWidth()
                    .height(76.dp)
                    .clickable {
                        if (focusTela.value) {

                        } else {
                            focusTela.value = true
                        }
                    },

                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD74C4C))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Excluir Conta",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Ir para Meu Perfil",
                        tint = Color.Black
                    )
                }
            }


        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(width = 115.dp, height = 53.dp)

            )
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
                    .padding(23.dp)
                    .border(
                        width = 0.dp,
                        shape = RoundedCornerShape(size = 16.dp),
                        color = Color.Transparent
                    )
            ) {
                Button(
                    onClick = {
                        focusTela.value = false
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
                    text = "Tem certeza de que deseja excluir a sua conta?",
                    fontSize = 16.sp,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    // modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                )
                Text(
                    text = "Essa ação não pode ser revertida",
                    fontSize = 16.sp,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center,
                    //modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Button(
                        onClick = {
                            focusTelaSegundaConfirmacao.value = true

                        },
                        colors = ButtonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        ),
                        modifier = Modifier
                            .offset(x = -20.dp, y = -10.dp)
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(size = 32.dp),
                                color = Color(0xff081F5C)
                            )
                    ) {
                        Text(
                            text = "Sim",
                            fontSize = 16.sp,
                            color = Color(0xff081F5C),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                        )
                    }
                    Button(
                        onClick = {
                            focusTela.value = false
                        },
                        colors = ButtonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        ),
                        modifier = Modifier.offset(x = -20.dp, y = -10.dp)
                    ) {
                        Text(
                            text = "Não",
                            fontSize = 16.sp,
                            color = Color(0xff081F5C),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                        )
                    }
                }
            }
        }
    }
    if (focusTelaSegundaConfirmacao.value) {
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
                    .padding(23.dp)
                    .border(
                        width = 0.dp,
                        shape = RoundedCornerShape(size = 16.dp),
                        color = Color.Transparent
                    )
            ) {
                Button(
                    onClick = {
                        focusTela.value = false
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
                    text = "Escreva seu endereço de email no campo a baixo para confirmar e deletar a conta",
                    fontSize = 16.sp,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    // modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                )
                Text(
                    text = "Sua conta não poderá ser restaurada após a exclusão",
                    fontSize = 16.sp,
                    color = Color(0xff081F5C),
                    textAlign = TextAlign.Center,
                    //modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
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
                Text(
                    text = mensagemErroState.value,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Button(
                        onClick = {
                            if (emailState.value == "") {
                                mensagemErroState.value = "Todos os campos devem ser preenchidos"
                                umError.value = true
                            } else if (emailState.value != email) {
                                mensagemErroState.value =
                                    "Verifique se preencheu corretamente seu endereço de email"
                                umError.value = true
                            } else {
                                if (tipoUsuario == "aluno") {
                                    val callDellAluno = RetrofitFactory().getUsuarioService()
                                        .setDellAluno(id.toInt())
                                    callDellAluno.enqueue(object : Callback<ResultAluno> {
                                        override fun onResponse(
                                            p0: Call<ResultAluno>,
                                            p1: Response<ResultAluno>
                                        ) {
                                            val alunoResponse = p1.body()
                                            if (p1.isSuccessful) {
                                                Log.i("CALMA", alunoResponse.toString())
                                                controleDeNavegacao.navigate("inicial")
                                            } else {
                                                val errorBody = p1.errorBody()?.string()
                                                val gson = Gson()
                                                val usuarioSalvo = gson.fromJson(
                                                    errorBody,
                                                    ResultAluno::class.java
                                                )
                                                mensagemErroState.value = usuarioSalvo.message
                                                umError.value = true
                                                Log.i("CALMA", alunoResponse?.message!!.toString())
                                            }
                                        }

                                        override fun onFailure(
                                            p0: Call<ResultAluno>,
                                            p1: Throwable
                                        ) {
                                            mensagemErroState.value =
                                                "Verifique se esta conectado a Internet ou tente novamente mais tarde"
                                            umError.value = true
                                            Log.i("ERRO_DELETAR_PERFIL", p1.toString())
                                        }
                                    })
                                } else {
                                    val callDellProfessor = RetrofitFactory().getUsuarioService()
                                        .setDellProfessor(id.toInt())
                                    callDellProfessor.enqueue(object : Callback<ResultProfessor> {
                                        override fun onResponse(
                                            p0: Call<ResultProfessor>,
                                            p1: Response<ResultProfessor>
                                        ) {
                                            val professorResponse = p1.body()
                                            if (p1.isSuccessful) {
                                                controleDeNavegacao.navigate("inicial")

                                                Log.i("CALMA", professorResponse.toString())

                                            } else {
                                                val errorBody = p1.errorBody()?.string()
                                                val gson = Gson()
                                                val usuarioSalvo = gson.fromJson(
                                                    errorBody,
                                                    ResultAluno::class.java
                                                )
                                                mensagemErroState.value = usuarioSalvo.message
                                                umError.value = true
                                                Log.i(
                                                    "CALMA",
                                                    professorResponse?.message!!.toString()
                                                )
                                            }
                                        }

                                        override fun onFailure(
                                            p0: Call<ResultProfessor>,
                                            p1: Throwable
                                        ) {
                                            mensagemErroState.value =
                                                "Verifique se esta conectado a Internet ou tente novamente mais tarde"
                                            umError.value = true
                                            Log.i("ERRO_DELETAR_PERFIL", p1.toString())
                                        }
                                    })
                                }
                            }
                        },
                        colors = ButtonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        ),
                        modifier = Modifier
                            .offset(x = -20.dp, y = -10.dp)
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(size = 32.dp),
                                color = Color(0xff081F5C)
                            )
                    ) {
                        Text(
                            text = "Sim",
                            fontSize = 16.sp,
                            color = Color(0xff081F5C),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                        )
                    }
                    Button(
                        onClick = {
                            focusTela.value = false
                            focusTelaSegundaConfirmacao.value = false
                        },
                        colors = ButtonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        ),
                        modifier = Modifier.offset(x = -20.dp, y = -10.dp)
                    ) {
                        Text(
                            text = "Não",
                            fontSize = 16.sp,
                            color = Color(0xff081F5C),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.offset(x = 0.dp, y = -10.dp)
                        )
                    }
                }
            }
        }
    }
}

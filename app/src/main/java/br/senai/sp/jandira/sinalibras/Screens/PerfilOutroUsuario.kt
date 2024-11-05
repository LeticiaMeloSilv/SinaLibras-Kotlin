package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PerfilOutroUsuario(
    controleDeNavegacao: NavHostController,
    tipoUsuario: String,
    id: String,
    fotoPerfil: String,
    idOutroUsuario: String,
    tipoOutroUsuario: String
) {


//    var dadosPerfil by remember {
//        mutableStateOf(Result())
//    }
    var dadosPerfilAluno by remember {
        mutableStateOf(Aluno())
    }
    var dadosPerfilProfessor by remember {
        mutableStateOf(Professor())
    }
    var funcionouState by remember {
        mutableStateOf(false)
    }
    var erroState by remember {
        mutableStateOf(false)
    }

    if (tipoOutroUsuario == "aluno") {
        Log.i("CALMA", "CALMA")

        val callAlunoById = RetrofitFactory().getUsuarioService().getAlunoId(idOutroUsuario.toInt())
        callAlunoById.enqueue(object : Callback<ResultAluno> {
            override fun onResponse(p0: Call<ResultAluno>, p1: Response<ResultAluno>) {
                val alunoResponse = p1.body()
                if (p1.isSuccessful) {

                    dadosPerfilAluno = alunoResponse?.aluno!!
                    Log.i("CALMA", dadosPerfilAluno.toString())

                    funcionouState = true

                } else {
                    Log.i("CALMA", alunoResponse?.message!!.toString())
                    erroState = true
                }
            }

            override fun onFailure(p0: Call<ResultAluno>, p1: Throwable) {
                Log.i("ERRO_PERFIL", p1.toString())
                erroState = true
            }
        })
    } else {
        val callProfessorById =
            RetrofitFactory().getUsuarioService().getProfessorId(idOutroUsuario.toInt())
        callProfessorById.enqueue(object : Callback<ResultProfessor> {
            override fun onResponse(p0: Call<ResultProfessor>, p1: Response<ResultProfessor>) {
                val professorResponse = p1.body()
                if (p1.isSuccessful) {
                    dadosPerfilProfessor = professorResponse?.professor!!
                    funcionouState = true

                } else {
                    erroState = true
                    Log.i("API Error", "Null response body")
                }
            }

            override fun onFailure(p0: Call<ResultProfessor>, p1: Throwable) {
                erroState = true

                Log.i("ERRO_OUTRO_PERFIL", p1.toString())
            }


        })

    }

    if (!funcionouState && !erroState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFC7E2FE), Color(0xFF345ADE))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logotipo SinaLibras",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "SinaLibras",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F51B5)
                )

                Spacer(modifier = Modifier.height(32.dp))

                //isso q faz o circulo q roda
                CircularProgressIndicator(
                    color = Color(0xFF3F51B5),
                    strokeWidth = 4.dp,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

    } else if (funcionouState) {
        if (tipoOutroUsuario == "aluno") {
            val aluno = dadosPerfilAluno

            val painter: Painter =
                if (!aluno.foto_perfil.isNullOrEmpty()) {
                    rememberAsyncImagePainter(model = aluno.foto_perfil)
                } else {
                    painterResource(id = R.drawable.perfil)
                }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD0E6FF))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                controleDeNavegacao.navigate("feed?id=${aluno.id_aluno}&tipoUsuario=${tipoUsuario}&fotoPerfil=${aluno.foto_perfil}")
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
                            text = "Perfil",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 26.sp,
                            modifier = Modifier.weight(1f)
                        )

                    }
                    Image(

                        painter = painter,
                        contentDescription = "foto de Perfil",
                        modifier = Modifier
                            .size(width = 170.dp, height = 170.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(CircleShape), // Aplica a forma circular
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = aluno.nome.uppercase(),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.selo_aluno),
                        contentDescription = "tag",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .height(32.dp)
                            .border(2.dp, color = Color.Black)

                    )

                }

                //btn manda mens
                Button(
                    onClick = { controleDeNavegacao.navigate("chatEspecifico?idDoOutroUsuario=${idOutroUsuario}}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}&tipoOutroUsuario=${tipoOutroUsuario}")},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F1951)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(3.dp)
                        .height(48.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.chat),
                            contentDescription = "Ícone de mensagem",
                            tint = Color(0xFFD1E3FF),
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Mensagem",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD1E3FF)
                        )
                    }
                }

                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    colors = CardColors(
                        containerColor = Color(0xFFA5D1FF),
                        contentColor = Color(0xFFA5D1FF),
                        disabledContentColor = Color(0xFFA5D1FF),
                        disabledContainerColor = Color(0xFFA5D1FF)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 38.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable { controleDeNavegacao.navigate("chat?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}") }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.chat),
                                contentDescription = "Chat Icon",
                                modifier = Modifier.size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Chat",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable { controleDeNavegacao.navigate("implementacao?id=${id}&tipoUsuario=${tipoUsuario}") }

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.atividades),
                                contentDescription = "Activities Icon",
                                modifier = Modifier
                                    .size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Atividades",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)

                            )
                        }


                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFA5D1FF))
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            if (tipoUsuario == "aluno") {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.rank
                                    ),
                                    contentDescription = "Profile Icon",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .offset((-10).dp, 0.dp)
                                        .clickable { controleDeNavegacao.navigate("implementacao?id=${id}&tipoUsuario=${tipoUsuario}") },
                                    contentScale = ContentScale.Fit
                                )
                            } else {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.mais
                                    ),
                                    contentDescription = "Profile Icon",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .offset((-10).dp, 0.dp)
                                        .clickable { controleDeNavegacao.navigate("criar?id=${id}&tipoUsuario=${tipoUsuario}") },
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    controleDeNavegacao.navigate("modulos?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.videos),
                                contentDescription = "Classes Icon",
                                modifier = Modifier
                                    .size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Aulas",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.feed),
                                contentDescription = "Menu Icon",
                                modifier = Modifier
                                    .size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Feed",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Box(modifier = Modifier
                                .height(2.dp)
                                .background(color = Color(0xff3459DE)))
                        }
                    }

                }

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(12.dp)
//                        .height(330.dp)
//                ) {
//
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        shape = RoundedCornerShape(16.dp),
//                        elevation = CardDefaults.cardElevation(8.dp),
//                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)) // Cor de fundo azul claro
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//// Ícone de "like"
//                            Image(
//                                painter = painterResource(id = R.drawable.logo),
//                                contentDescription = "perfil",
//                                modifier = Modifier
//                                    .size(width = 60.dp, height = 70.dp)
//
//                            )
//
//                            Spacer(modifier = Modifier.width(16.dp))
//
//                            Column(
//                                modifier = Modifier.weight(1f)
//                            ) {
//// Título
//                                Text(
//                                    text = "NÚMEROS",
//                                    fontSize = 18.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.Black
//                                )
//
//                                Spacer(modifier = Modifier.height(8.dp))
//
//// Botões de Vídeos e Atividades
//                                Row {
//                                    Button(
//                                        onClick = { /* TODO: Ação dos Vídeos */ },
//                                        colors = ButtonDefaults.buttonColors(
//                                            containerColor = Color(
//                                                0x00FFFFFF
//                                            )
//                                        ),
//                                        modifier = Modifier
//                                            .padding(end = 8.dp)
//                                            .border(
//                                                border = BorderStroke(2.dp, Color(0xFF345ADE)),
//                                                shape = RoundedCornerShape(16.dp)
//                                            ),
//                                        contentPadding = PaddingValues(
//                                            horizontal = 12.dp,
//                                            vertical = 4.dp
//                                        )
//                                    ) {
//                                        Text(
//                                            text = "4 Vídeos",
//                                            color = Color(0xFF345ADE),
//                                            fontSize = 12.sp
//                                        )
//                                    }
//
//                                    Button(
//                                        modifier = Modifier.border(
//                                            border = BorderStroke(2.dp, Color(0xFFFEC608)),
//                                            shape = RoundedCornerShape(16.dp)
//                                        ),
//                                        onClick = { /* TODO: Ação das Atividades */ },
//                                        colors = ButtonDefaults.buttonColors(
//                                            containerColor = Color(
//                                                0x00FFFFFF
//                                            )
//                                        ),
//
//                                        contentPadding = PaddingValues(
//                                            horizontal = 5.dp,
//                                            vertical = 4.dp
//                                        )
//                                    ) {
//                                        Text(
//                                            text = "2 Atividades",
//                                            color = Color(0xFFFEC608),
//                                            fontSize = 12.sp
//                                        )
//                                    }
//                                }
//                            }
//
//                            Spacer(modifier = Modifier.width(16.dp))
//
//                            Box(
//                                contentAlignment = Alignment.Center,
//                                modifier = Modifier.size(48.dp)
//                            ) {
//                                CircularProgressIndicator(
//                                    progress = 0.65f,
//                                    modifier = Modifier.fillMaxSize(),
//                                    color = Color(0xFF4CAF50),
//                                    strokeWidth = 6.dp
//                                )
//
//                                Text(
//                                    text = "65%",
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.Black
//                                )
//                            }
//                        }
//                    }
//                }
            }
        } else {
            val aluno = dadosPerfilAluno

            val painter: Painter =
                if (!aluno.foto_perfil.isNullOrEmpty()) {
                    rememberAsyncImagePainter(model = aluno.foto_perfil)
                } else {
                    painterResource(id = R.drawable.perfil)
                }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD0E6FF))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                controleDeNavegacao.navigate("feed?id=${aluno.id_aluno}&tipoUsuario=${tipoUsuario}&fotoPerfil=${aluno.foto_perfil}")
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
                            text = "Perfil",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 26.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = {
                                //mn, eu deria pegar por id em todas as telas, mas nn é legal fzr varias requisicoes no banco, eu poderia enviar diretamente o usuario aluno, mas dai, pra conseguir pegar as informacoes, eu teria que criar tres variaveis só pra pegar o valor de cada item, poderia usar viewmodel mas nmo tempo de tcc que tenho sobrano, é melhor não gastar aprendendo, por esse motivo, vou apelar para o q na minha cabeca é mais facil, e vou anotar o q é cada um
                                controleDeNavegacao.navigate("configuracoes?id=${aluno.id_aluno}&email=${aluno.email}&nome=${aluno.nome}&dataNascimento=${aluno.data_nascimento}&fotoPerfil=${aluno.foto_perfil}&tipoUsuario=${tipoUsuario}")
                            },
                            colors = ButtonColors(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent
                            )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.configuracoes),
                                contentDescription = "Botao Configurações de conta",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                    Image(

                        painter = painter,
                        contentDescription = "foto de Perfil",
                        modifier = Modifier
                            .size(width = 170.dp, height = 170.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(CircleShape), // Aplica a forma circular
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = aluno.nome.uppercase(),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.selo_aluno),
                        contentDescription = "tag",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .height(32.dp)
                            .border(2.dp, color = Color.Black)

                    )

                }

                //btn manda mens
                Button(
                    onClick = { /* Ação do botão */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F1951)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(3.dp)
                        .height(48.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.chat),
                            contentDescription = "Ícone de mensagem",
                            tint = Color(0xFFD1E3FF),
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Mensagem",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD1E3FF)
                        )
                    }
                }

                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    colors = CardColors(
                        containerColor = Color(0xFFA5D1FF),
                        contentColor = Color(0xFFA5D1FF),
                        disabledContentColor = Color(0xFFA5D1FF),
                        disabledContainerColor = Color(0xFFA5D1FF)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 38.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable { controleDeNavegacao.navigate("chat?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}") }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.chat),
                                contentDescription = "Chat Icon",
                                modifier = Modifier.size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Chat",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable { controleDeNavegacao.navigate("implementacao?id=${id}&tipoUsuario=${tipoUsuario}") }

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.atividades),
                                contentDescription = "Activities Icon",
                                modifier = Modifier
                                    .size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Atividades",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)

                            )
                        }


                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFA5D1FF))
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            if (tipoUsuario == "aluno") {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.rank
                                    ),
                                    contentDescription = "Profile Icon",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .offset((-10).dp, 0.dp)
                                        .clickable { controleDeNavegacao.navigate("implementacao?id=${id}&tipoUsuario=${tipoUsuario}") },
                                    contentScale = ContentScale.Fit
                                )
                            } else {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.mais
                                    ),
                                    contentDescription = "Profile Icon",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .offset((-10).dp, 0.dp)
                                        .clickable { controleDeNavegacao.navigate("criar?id=${id}&tipoUsuario=${tipoUsuario}") },
                                    contentScale = ContentScale.Fit
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    controleDeNavegacao.navigate("modulos?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.videos),
                                contentDescription = "Classes Icon",
                                modifier = Modifier
                                    .size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Aulas",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.feed),
                                contentDescription = "Menu Icon",
                                modifier = Modifier
                                    .size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "Feed",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Box(modifier = Modifier
                                .height(2.dp)
                                .background(color = Color(0xff3459DE)))
                        }
                    }

                }

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(12.dp)
//                        .height(330.dp)
//                ) {
//
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        shape = RoundedCornerShape(16.dp),
//                        elevation = CardDefaults.cardElevation(8.dp),
//                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)) // Cor de fundo azul claro
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//// Ícone de "like"
//                            Image(
//                                painter = painterResource(id = R.drawable.logo),
//                                contentDescription = "perfil",
//                                modifier = Modifier
//                                    .size(width = 60.dp, height = 70.dp)
//
//                            )
//
//                            Spacer(modifier = Modifier.width(16.dp))
//
//                            Column(
//                                modifier = Modifier.weight(1f)
//                            ) {
//// Título
//                                Text(
//                                    text = "NÚMEROS",
//                                    fontSize = 18.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.Black
//                                )
//
//                                Spacer(modifier = Modifier.height(8.dp))
//
//// Botões de Vídeos e Atividades
//                                Row {
//                                    Button(
//                                        onClick = { /* TODO: Ação dos Vídeos */ },
//                                        colors = ButtonDefaults.buttonColors(
//                                            containerColor = Color(
//                                                0x00FFFFFF
//                                            )
//                                        ),
//                                        modifier = Modifier
//                                            .padding(end = 8.dp)
//                                            .border(
//                                                border = BorderStroke(2.dp, Color(0xFF345ADE)),
//                                                shape = RoundedCornerShape(16.dp)
//                                            ),
//                                        contentPadding = PaddingValues(
//                                            horizontal = 12.dp,
//                                            vertical = 4.dp
//                                        )
//                                    ) {
//                                        Text(
//                                            text = "4 Vídeos",
//                                            color = Color(0xFF345ADE),
//                                            fontSize = 12.sp
//                                        )
//                                    }
//
//                                    Button(
//                                        modifier = Modifier.border(
//                                            border = BorderStroke(2.dp, Color(0xFFFEC608)),
//                                            shape = RoundedCornerShape(16.dp)
//                                        ),
//                                        onClick = { /* TODO: Ação das Atividades */ },
//                                        colors = ButtonDefaults.buttonColors(
//                                            containerColor = Color(
//                                                0x00FFFFFF
//                                            )
//                                        ),
//
//                                        contentPadding = PaddingValues(
//                                            horizontal = 5.dp,
//                                            vertical = 4.dp
//                                        )
//                                    ) {
//                                        Text(
//                                            text = "2 Atividades",
//                                            color = Color(0xFFFEC608),
//                                            fontSize = 12.sp
//                                        )
//                                    }
//                                }
//                            }
//
//                            Spacer(modifier = Modifier.width(16.dp))
//
//                            Box(
//                                contentAlignment = Alignment.Center,
//                                modifier = Modifier.size(48.dp)
//                            ) {
//                                CircularProgressIndicator(
//                                    progress = 0.65f,
//                                    modifier = Modifier.fillMaxSize(),
//                                    color = Color(0xFF4CAF50),
//                                    strokeWidth = 6.dp
//                                )
//
//                                Text(
//                                    text = "65%",
//                                    fontSize = 14.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = Color.Black
//                                )
//                            }
//                        }
//                    }
//                }
            }
        }

    }
    else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD0E6FF))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.erro),
                contentDescription = "logo",
                modifier = Modifier

            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "ERRO!!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "mande uma\nmensagem para o\ntime de suporte",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}
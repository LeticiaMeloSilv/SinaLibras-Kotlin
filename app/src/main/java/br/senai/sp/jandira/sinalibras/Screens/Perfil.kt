package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
fun Perfil(controleDeNavegacao: NavHostController, recebido: String) {
    val partes = recebido.split("*")
    val id = partes[0].toInt()
    val tipoUsuario = partes[1]

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

    if (tipoUsuario == "aluno") {
        Log.i("CALMA","CALMA")

        val callAlunoById = RetrofitFactory().getUsuarioService().getAlunoId(id)
        callAlunoById.enqueue(object : Callback<ResultAluno> {
            override fun onResponse(p0: Call<ResultAluno>, p1: Response<ResultAluno>) {
                val alunoResponse = p1.body()
                if (p1.isSuccessful) {

                    dadosPerfilAluno = alunoResponse?.aluno!!
                    Log.i("CALMA", dadosPerfilAluno.toString())

                    funcionouState = true

                } else {
                    Log.i("CALMA", alunoResponse?.message!!.toString())
                }
            }

            override fun onFailure(p0: Call<ResultAluno>, p1: Throwable) {
                Log.i("ERRO_PERFIL", p1.toString())
            }
        })
    } else {
        Log.i("ID", id.toString())
        val callProfessorById = RetrofitFactory().getUsuarioService().getProfessorId(id)
        callProfessorById.enqueue(object : Callback<ResultProfessor> {
            override fun onResponse(p0: Call<ResultProfessor>, p1: Response<ResultProfessor>) {
                val professorResponse = p1.body()
                if (p1.isSuccessful) {

                    dadosPerfilProfessor = professorResponse?.professor!!

                    funcionouState = true

                } else {
                    Log.i("API Error", "Null response body")
                }
            }

            override fun onFailure(p0: Call<ResultProfessor>, p1: Throwable) {
                Log.i("ERRO_PERFIL", p1.toString())
            }


        })

    }


    if (funcionouState) {
        if (tipoUsuario == "aluno") {
            val aluno = dadosPerfilAluno

            val painter: Painter =
                if (aluno.foto_perfil != null && aluno.foto_perfil != "null" && aluno.foto_perfil.isNotEmpty()) {
                    rememberAsyncImagePainter(model = aluno.foto_perfil)
                } else {
                    painterResource(id = R.drawable.perfil)
                }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFC7E2FE))
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
                                controleDeNavegacao.navigate("feed")
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
                                controleDeNavegacao.navigate("configuracoes/${aluno}*aluno")
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

                    )

                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .height(330.dp)
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)) // Cor de fundo azul claro
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
// Ícone de "like"
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "perfil",
                                modifier = Modifier
                                    .size(width = 60.dp, height = 70.dp)

                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
// Título
                                Text(
                                    text = "NÚMEROS",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(8.dp))

// Botões de Vídeos e Atividades
                                Row {
                                    Button(
                                        onClick = { /* TODO: Ação dos Vídeos */ },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(
                                                0x00FFFFFF
                                            )
                                        ),
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .border(
                                                border = BorderStroke(2.dp, Color(0xFF345ADE)),
                                                shape = RoundedCornerShape(16.dp)
                                            ),
                                        contentPadding = PaddingValues(
                                            horizontal = 12.dp,
                                            vertical = 4.dp
                                        )
                                    ) {
                                        Text(
                                            text = "4 Vídeos",
                                            color = Color(0xFF345ADE),
                                            fontSize = 12.sp
                                        )
                                    }

                                    Button(
                                        modifier = Modifier.border(
                                            border = BorderStroke(2.dp, Color(0xFFFEC608)),
                                            shape = RoundedCornerShape(16.dp)
                                        ),
                                        onClick = { /* TODO: Ação das Atividades */ },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(
                                                0x00FFFFFF
                                            )
                                        ),

                                        contentPadding = PaddingValues(
                                            horizontal = 5.dp,
                                            vertical = 4.dp
                                        )
                                    ) {
                                        Text(
                                            text = "2 Atividades",
                                            color = Color(0xFFFEC608),
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.size(48.dp)
                            ) {
                                CircularProgressIndicator(
                                    progress = 0.65f,
                                    modifier = Modifier.fillMaxSize(),
                                    color = Color(0xFF4CAF50),
                                    strokeWidth = 6.dp
                                )

                                Text(
                                    text = "65%",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        } else {
            val professor = dadosPerfilProfessor

            val painter: Painter =
                if (professor.foto_perfil != null && professor.foto_perfil != "null" && professor.foto_perfil.isNotEmpty()) {
                    rememberAsyncImagePainter(model = professor.foto_perfil)
                } else {
                    painterResource(id = R.drawable.perfil)
                }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFC7E2FE))
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
                        Text(
                            text = "Perfil",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 26.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = {
                                controleDeNavegacao.navigate("editarPerfil/${professor}*professor")
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
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = professor.nome.uppercase(),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.selo_professor),
                        contentDescription = "tag",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .height(32.dp)

                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .height(330.dp)
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)) // Cor de fundo azul claro
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
// Ícone de "like"
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "perfil",
                                modifier = Modifier
                                    .size(width = 60.dp, height = 70.dp)

                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
// Título
                                Text(
                                    text = "NÚMEROS",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(8.dp))

// Botões de Vídeos e Atividades
                                Row {
                                    Button(
                                        onClick = { /* TODO: Ação dos Vídeos */ },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(
                                                0x00FFFFFF
                                            )
                                        ),
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .border(
                                                border = BorderStroke(2.dp, Color(0xFF345ADE)),
                                                shape = RoundedCornerShape(16.dp)
                                            ),
                                        contentPadding = PaddingValues(
                                            horizontal = 12.dp,
                                            vertical = 4.dp
                                        )
                                    ) {
                                        Text(
                                            text = "4 Vídeos",
                                            color = Color(0xFF345ADE),
                                            fontSize = 12.sp
                                        )
                                    }

                                    Button(
                                        modifier = Modifier.border(
                                            border = BorderStroke(2.dp, Color(0xFFFEC608)),
                                            shape = RoundedCornerShape(16.dp)
                                        ),
                                        onClick = { /* TODO: Ação das Atividades */ },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(
                                                0x00FFFFFF
                                            )
                                        ),

                                        contentPadding = PaddingValues(
                                            horizontal = 5.dp,
                                            vertical = 4.dp
                                        )
                                    ) {
                                        Text(
                                            text = "2 Atividades",
                                            color = Color(0xFFFEC608),
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.size(48.dp)
                            ) {
                                CircularProgressIndicator(
                                    progress = 0.65f,
                                    modifier = Modifier.fillMaxSize(),
                                    color = Color(0xFF4CAF50),
                                    strokeWidth = 6.dp
                                )

                                Text(
                                    text = "65%",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
        } else {
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
                        text = "Ocorreu um erro inesperado. Contate o time de suporte",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Chat(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String
) {
    var dadosProfessores by remember {
        mutableStateOf(Professor())
    }
    var funcionouState by remember {
        mutableStateOf(false)
    }
    val callProfessores =
        RetrofitFactory().getUsuarioService().getAllProfessores()

    callProfessores.enqueue(object : Callback<ResultProfessor> {
        override fun onResponse(p0: Call<ResultProfessor>, p1: Response<ResultProfessor>) {
            val professorResponse = p1.body()
            Log.i("ALUNO", professorResponse.toString())
            if (p1.isSuccessful) {
                if (professorResponse != null) {
                    funcionouState = true
                    dadosProfessores = professorResponse.professor!!
                }

            } else {
                Log.i("CALMA", professorResponse?.message!!.toString())
            }
        }

        override fun onFailure(p0: Call<ResultProfessor>, p1: Throwable) {
            Log.i("ERRO_VIDEO", p1.toString())
        }
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color(0xFF04509C),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //barra de pesquisa
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Pesquisar por usuario...",
                        tint = Color(0xFF04509C)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Pesquisar por usuario...",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Professores disponiveis",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
            ) {
                //pegar todos os professores
                items(5) { dadosProfessor ->
                    Log.i("PROFESSOR", dadosProfessor.toString())
                    Card(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(width = 64.dp, height = 64.dp)
                            .clickable{
                                controleDeNavegacao.navigate("outroPerfil?id=${id}&idOutroUsuario=${tipoUsuario}&tipoUsuario=${tipoUsuario}&tipoOutroUsuario=${dadosProfessor}&fotoPerfil=${fotoPerfil}")
                                      },
                        shape = CircleShape,
                        colors = CardDefaults
                            .cardColors(
                                containerColor = Color(0xFFCF06F0)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.perfil),
                                contentDescription = "foto de perfil do usuario${dadosProfessor}",
                                modifier = Modifier
                                    .size(25.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "primeiroNome",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Recentes",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 22.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))


            LazyColumn(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(319.dp)
                    .height(349.dp)
            ) {
                //pegar todos os usuarios q o outro usuario ja mandou msg
                items(5) { dadosUsuarios ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54. dp)
                            .clickable{
                                controleDeNavegacao.navigate("chatEspecifico?idDoOutroUsuario=${dadosUsuarios}}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}&tipoOutroUsuario=professor")
                                      },

                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.perfil),
                                contentDescription = "foto de perfil do usuario${dadosUsuarios}",
                                modifier = Modifier
                                    .size(38.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(
                                text = "nome do usuario",
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp)
                            )

                        }
                    }
                }
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
    }
}
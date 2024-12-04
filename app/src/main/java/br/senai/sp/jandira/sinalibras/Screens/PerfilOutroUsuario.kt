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
import br.senai.sp.jandira.sinalibras.model.ResultVideo
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
    var focus by remember {
        mutableStateOf(false)
    }
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
                    Log.i("fatal",p1.toString())
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
                    Log.i("fatal", dadosPerfilProfessor.toString())


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
        val professor = dadosPerfilProfessor

        val painter: Painter =
            if (!professor.foto_perfil.isNullOrEmpty()) {
                rememberAsyncImagePainter(model = professor.foto_perfil, placeholder =painterResource(id= R.drawable.perfil))
            } else {
                painterResource(id = R.drawable.perfil)
            }


        Box(
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
                            controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
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
                        text = "Perfil de ${dadosPerfilProfessor.nome}",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 26.sp,
                        modifier = Modifier.weight(1f)
                    )

                }
                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painter,
                    contentDescription = "foto de Perfil",
                    modifier = Modifier
                        .size(width = 170.dp, height = 170.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
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
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.selo_professor),
                    contentDescription = "tag",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(40.dp)
                )

            }

            //btn manda mens
            Button(
                onClick = { //AQ COLOCA O LINK PRA TELA DE VIDEO CHAMADA
                     },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F1951)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(bottom = 110.dp)
                    .height(48.dp)
                    .align(Alignment.BottomCenter),

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
            if (focus) {
                Box(modifier=Modifier.fillMaxSize().background(color=Color(0x68090A1E))){
                    Column(
                        modifier = Modifier
                            .width(258.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .align(Alignment.BottomCenter)
                            .padding(start=16.dp,end=16.dp,top=16.dp, bottom = 105.dp)
                    ) {
                        Button(
                            onClick = {
                                focus = false
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFC7E2FE))
                                .border(1.dp, Color.Black)
                                .clickable{
                                    controleDeNavegacao.navigate("postarVideo?id=${id}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                                }
                                .padding(horizontal = 12.dp, vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Adicionar aula",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.Black
                            )
                            Image(
                                painter = painterResource(id = R.drawable.videoaula),
                                contentDescription = "adicionar aula",
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFC7E2FE))
                                .border(1.dp, Color.Black)
                                .clickable{
                                    controleDeNavegacao.navigate("postarPostagem?id=${id}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
                                }
                                .padding(horizontal = 12.dp, vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Adicionar post",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.Black
                            )
                            Image(
                                painter = painterResource(id = R.drawable.postagem),
                                contentDescription = "adicionar postagem",
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }}
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
                            .clickable {
                                controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.perfil_icone),
                            contentDescription = "icone de perfil",
                            modifier = Modifier
                                .size(25.dp),
                            contentScale = ContentScale.Fit
                        )
                        Text(
                            text = "Perfil",
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
                                    .size(45.dp),
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
                                    .clickable { focus = true },
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
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .background(color = Color(0xff3459DE))
                        )
                    }
                }

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
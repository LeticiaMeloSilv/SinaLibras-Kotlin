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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultProfessores
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
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
        mutableStateOf(ResultProfessores())
    }

    var funcionouState by remember {
        mutableStateOf(false)
    }
    val service = RetrofitFactory().getUsuarioService()
    Log.i("CARALHO", "Service criado: $service")

    val callProfessores = service.getAllProfessores()
    Log.i("CARALHO", "Chamada criada: $callProfessores")
try{
    callProfessores.enqueue(object : Callback<ResultProfessores> {
        override fun onResponse(call: Call<ResultProfessores>, response: Response<ResultProfessores>) {
            Log.i("CARALHO", "Código de resposta: ${response.code()}")
            if (response.isSuccessful) {
                Log.i("CARALHO", "Resposta: ${response.body()}")
                funcionouState = true
                        dadosProfessores = professorResponse
            } else {
                Log.e("CARALHO", "Erro: ${response.errorBody()?.string()}")
            }        }

        override fun onFailure(call: Call<ResultProfessores>, t: Throwable) {
            Log.e("CARALHO", "Erro: ${t.message}", t)
        }
    })
}
catch(e:Exception){
    Log.i("CARALHO", e.toString())
}
//    callProfessores.enqueue(object : Callback<ResultProfessores> {
//        override fun onResponse(p0: Call<ResultProfessores>, p1: Response<ResultProfessores>) {
//            try{
//            val professorResponse = p1.body()
//            Log.i("CARALHO", "$professorResponse 77")
//            if (professorResponse != null) {
//                if (professorResponse.status_code==200) {
//                    if (professorResponse != null) {
//                        funcionouState = true
//                        dadosProfessores = professorResponse
//                        Log.i("CARALHO", "$professorResponse 82")
//                    } else{funcionouState=false
//                        Log.i("CARALHO", "PQP")
//                    }
//
//                } else {
//                    Log.i("CARALHO", "se for eu choro")
//                }
//            }
//            else{
//
//            }
//            }
//            catch (e: Exception){
//                Log.i("CARALHO", e.toString())
//            }
//        }
//        override fun onFailure(p0: Call<ResultProfessores>, p1: Throwable) {
//            Log.i("CARALHO", p1.toString())
//        }
//    })

    val painter: Painter =
        if (fotoPerfil != "" && fotoPerfil != "null" && fotoPerfil.isNotEmpty()) {
            rememberAsyncImagePainter(model = fotoPerfil)
        } else {
            painterResource(id = R.drawable.perfil)
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
    ) {
        Column(){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFFA5D1FF), CircleShape)
                    .clickable {
                        controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                    }
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "Recentes",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

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

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Professores disponiveis",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Log.i("ONDE",dadosProfessores.toString())

            Row(
            ) {
                dadosProfessores.professores!!.forEach {
                    professor->
                    val professorPainter: Painter =
                        if (funcionouState && !professor.foto_perfil.isNullOrEmpty()) {
                            rememberAsyncImagePainter(
                                model = professor.foto_perfil
                            )
                        } else {
                            painterResource(id = R.drawable.perfil)
                        }
                    Log.i("PROFESSOR", professor.toString())
                    Column(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable {
                                controleDeNavegacao.navigate(
                                    "outroPerfil?id=${id}&idOutroUsuario=${
                                        professor.id_professor
                                    }&tipoUsuario=${tipoUsuario}&tipoOutroUsuario=professor&fotoPerfil=${fotoPerfil}"
                                )
                            },

                        ) {

                        Image(
                            painter = professorPainter,
                            contentDescription = "foto de perfil do usuario${professor.nome}",
                            modifier = Modifier
                                .size(width = 64.dp, height = 64.dp),
                            contentScale = ContentScale.Fit
                        )
                        professor.nome.let {
                            Text(
                                text = it,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }

//        Column(
//            modifier = Modifier
//                .width(319.dp)
//                .padding(top=10.dp)
//        ) {
//            //pegar todos os usuarios q o outro usuario ja mandou msg
//            items(5) { dadosUsuarios ->
//                Card(
//                    modifier = Modifier
//                        .height(54.dp)
//                        .clickable {
//                            controleDeNavegacao.navigate("chatEspecifico?idDoOutroUsuario=${dadosUsuarios}}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}&tipoOutroUsuario=professor")
//                        },
//
//                    shape = RoundedCornerShape(16.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(horizontal = 16.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.perfil),
//                            contentDescription = "foto de perfil do usuario${dadosUsuarios}",
//                            modifier = Modifier
//                                .size(38.dp),
//                            contentScale = ContentScale.Fit
//                        )
//                        Text(
//                            text = "nome do usuario",
//                            fontWeight = FontWeight.Medium,
//                            fontSize = 18.sp,
//                            color = Color.Black,
//                            modifier = Modifier
//                                .weight(1f)
//                                .padding(horizontal = 8.dp)
//                        )
//                    }
//                }
//            }
//        }
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

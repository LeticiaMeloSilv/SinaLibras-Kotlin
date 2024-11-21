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
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import kotlin.math.round

@Composable
fun Perfil(controleDeNavegacao: NavHostController, tipoUsuario: String,id: String, fotoPerfil:String) {


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

    if (tipoUsuario == "aluno") {
        Log.i("CALMA", "CALMA")

        val callAlunoById = RetrofitFactory().getUsuarioService().getAlunoId(id.toInt())
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
        Log.i("ID", id.toString())
        val callProfessorById = RetrofitFactory().getUsuarioService().getProfessorId(id.toInt())
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

                Log.i("ERRO_PERFIL", p1.toString())
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
        if (tipoUsuario == "aluno") {
            val aluno = dadosPerfilAluno

            val painter: Painter =
                if (!aluno.foto_perfil.isNullOrEmpty()) {
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
                                //id,email,nome,dataDeNascimento,fotoDeOPerfil,tipoDeUsuario
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
                            .height(40.dp)
                            .clip(RoundedCornerShape(32.dp)),


                    )

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
            val professor = dadosPerfilProfessor
            var dadosVideos by remember{
                mutableStateOf(ResultVideo())
            }
            val painter: Painter =
                if (!professor.foto_perfil.isNullOrEmpty()) {
                    rememberAsyncImagePainter(model = professor.foto_perfil)
                } else {
                    painterResource(id = R.drawable.perfil)
                }
            val callVideosDoProfessor =
                RetrofitFactory().getVideoAulaService().getVideosProfessorById(id.toInt())

            callVideosDoProfessor.enqueue(object : Callback<ResultVideo> {
                override fun onResponse(p0: Call<ResultVideo>, p1: Response<ResultVideo>) {
                    val videoResponse = p1.body()
                    Log.i("ALUNO",videoResponse.toString())
                    if (p1.isSuccessful) {
                        Log.i("erroo",videoResponse.toString())

                        if (videoResponse != null) {
                            if (videoResponse.videos != null) {
                                funcionouState=true
                                dadosVideos= videoResponse
                                Log.i("erroo",videoResponse.toString())
                            }
                            else{
                                erroState = true
                            }
                        }
                        else{
                            Log.i("CALMA","ESTYIKL")
                            erroState = true

                        }

                    } else {
                        erroState = true

                        Log.i("CALMA", videoResponse?.message!!.toString())
                    }
                }

                override fun onFailure(p0: Call<ResultVideo>, p1: Throwable) {
                    erroState = true
                    Log.i("ERRO_VIDEO", p1.toString())
                }
            })


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
                                controleDeNavegacao.navigate("feed?id=${professor.id_professor}&tipoUsuario=${tipoUsuario}&fotoPerfil=${professor.foto_perfil}")
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
                                //id,email,nome,dataDeNascimento,fotoDeOPerfil,tipoDeUsuario
                                controleDeNavegacao.navigate("configuracoes?id=${professor.id_professor}&email=${professor.email}&nome=${professor.nome}&dataNascimento=${professor.data_nascimento}&fotoPerfil=${professor.foto_perfil}&tipoUsuario=${tipoUsuario}")
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
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,modifier=Modifier.fillMaxWidth()){
                    Button(onClick = {controleDeNavegacao.navigate("postarVideo?id=${id}&email=${professor.email}&nome=${professor.nome}&dataNascimento=${professor.data_nascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")}) {
                        Text(text="Postar Video")
                    }
                    Spacer(modifier=Modifier.width(20.dp))
                    Button(onClick = {controleDeNavegacao.navigate("postarPostagem?id=${id}&email=${professor.email}&nome=${professor.nome}&dataNascimento=${professor.data_nascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")}) {
                        Text(text="Postar Texto")
                    }
                }
                Spacer(modifier=Modifier.height(20.dp))
                if(dadosVideos.videos!=null){
                LazyColumn(modifier = Modifier.padding(horizontal=20.dp)) {
                    items(dadosVideos.videos ?: emptyList()) { video ->
                        Log.i("CALMA", dadosVideos.toString())
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFFFFF)
                            ),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                             //  .clickable { controleDeNavegacao.navigate("editarVideo?idDoVideo=${video.id_videoaula}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}&idModulo=${idModulo}&nomeModulo=${nomeModulo}") }
                        ) {
                            Column {
                                // Video thumbnail

                                Image(
                                    rememberAsyncImagePainter(model = video.url_video),
                                    contentDescription = "card do video",
                                    modifier = Modifier
                                        .height(180.dp)
                                        .fillMaxWidth(),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = video.titulo,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = video.data_cadastro, fontSize = 14.sp)
                                }
                            }}
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
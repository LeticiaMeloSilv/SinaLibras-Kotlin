package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.Quiz
import br.senai.sp.jandira.sinalibras.model.ResultQuiz
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.CardColors
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import br.senai.sp.jandira.sinalibras.model.RespostaUsuario
import br.senai.sp.jandira.sinalibras.model.ResultRespostaUsuario

var respostasUsuario = mutableListOf<RespostaUsuario>()

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerScreen(videoUri: Uri) {
    val context = LocalContext.current
    val playerView = remember { PlayerView(context) }

    val player = remember { ExoPlayer.Builder(context).build() }
    playerView.player = player
    val id = videoUri.toString().substringAfter("/d/").substringBefore("/view")
    val downloadLink = "https://drive.google.com/uc?export=download&id=$id"

    player.volume = 0f

    val mediaItem = MediaItem.fromUri(Uri.parse(downloadLink))
    player.setMediaItem(mediaItem)
    player.prepare()
    player.play()
    LaunchedEffect(mediaItem) {
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    AndroidView(factory = { playerView })
}


@Composable
fun QuizCard(quiz: Quiz, id: Int) {
    var alternativaSelecionada by remember { mutableStateOf<Int?>(null) }

    Card(
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = quiz.pergunta,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(20.dp))

            val videoUri = Uri.parse(quiz.video)
            Box(modifier = Modifier.height(200.dp)) {
                VideoPlayerScreen(videoUri)
            }
            Spacer(modifier = Modifier.height(20.dp))

            quiz.alternativas?.chunked(2)?.forEach { alternativasLinha ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    alternativasLinha.forEach { alternativa ->
                        Column {
                            Text(
                                text = alternativa.alternativa,
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .clickable(
                                        enabled = alternativaSelecionada == null || alternativaSelecionada == alternativa.id_alternativa
                                    ) {
                                        alternativaSelecionada = alternativa.id_alternativa
                                        respostasUsuario.add(
                                            RespostaUsuario(
                                                alternativa.id_alternativa,
                                                id
                                            )
                                        )
                                    }
                                    .background(
                                        if (alternativaSelecionada == alternativa.id_alternativa) Color.LightGray else Color.Transparent,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizScreen(quizList: List<Quiz>, idUsuario: Int) {
    LazyColumn {
        items(quizList) { quiz ->
            QuizCard(quiz, idUsuario)
        }
    }
}

@Composable
fun Quiz(controleDeNavegacao: NavHostController, recebido: String) {
    var dadosPerfil by remember {
        mutableStateOf(ResultQuiz())
    }
    var funcionouState by remember {
        mutableStateOf(false)
    }
    val partes = recebido.split("*")
    val idFornecido = partes[0]
    val emailFornecido = partes[1]

    val callQuestions = RetrofitFactory().getQuizService().getAllQuestoes()
    callQuestions.enqueue(object : Callback<ResultQuiz> {
        override fun onResponse(p0: Call<ResultQuiz>, p1: Response<ResultQuiz>) {
            val alunoResponse = p1.body()
            if (alunoResponse == null) {
                Log.i("ERRO_PERGUNTAS", p1.toString())
            } else {
                Log.i("TAG", alunoResponse.toString())
                funcionouState = true
                dadosPerfil = alunoResponse
            }
        }

        override fun onFailure(p0: Call<ResultQuiz>, p1: Throwable) {
            Log.i("ERRO_PERFIL", p1.toString())
        }


    })
    //gradiente do background
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFC7E2FE), Color(0xFF345ADE)),
        start = Offset.Zero,
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )
    if (funcionouState) {
        Column(
            modifier = Modifier
                .background(brush = gradientBrush)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(width = 200.dp, height = 130.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    dadosPerfil.questoes?.let { QuizScreen(it, idFornecido.toInt()) }
                }
            }
            Button(
                onClick = {

                    val respostas = respostasUsuario
                    var showErrorDialog by mutableStateOf(false)

                    val callQuestions =
                        RetrofitFactory().getQuizService().salvarRespostas(respostas)
                    callQuestions.enqueue(object : Callback<ResultRespostaUsuario> {
                        override fun onResponse(
                            p0: Call<ResultRespostaUsuario>,
                            p1: Response<ResultRespostaUsuario>
                        ) {
                            val response = p1.body()
                            if (response == null) {
                                Log.i("ERRO_RESPOSTAS", p1.toString())
                                showErrorDialog = true
                            } else {
                                //SUBSTITUIR PELA PONTUAÇÃO CERTA
                                controleDeNavegacao.navigate("acerto/${70}*${emailFornecido}")
                                Log.i("TAG", response.toString())
                            }
                        }

                        override fun onFailure(p0: Call<ResultRespostaUsuario>, p1: Throwable) {
                            Log.i("ERRO_RESPOSTAS", p1.toString())
                        }
                    })

                },
                modifier = Modifier
                    .padding(16.dp)
                    .width(298.dp)
                    .height(54.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF3459DE)
                )

            ) {
                Text(text = "Enviar", fontSize = 24.sp)
            }

        }
    }
}



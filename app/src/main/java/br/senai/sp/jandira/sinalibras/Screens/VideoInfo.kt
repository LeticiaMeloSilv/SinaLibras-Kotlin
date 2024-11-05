package br.senai.sp.jandira.sinalibras.Screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultVideo
import br.senai.sp.jandira.sinalibras.model.VideoAula
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun PlayerVideo(videoUri: Uri) {
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
fun VideoInfo(
    controleDeNavegacao: NavHostController,
    idDoVideo: String,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String,
    idModulo: String,
    nomeModulo: String
) {

    var dadosVideoAula by remember {
        mutableStateOf(VideoAula())
    }
    var funcionouState by remember {
        mutableStateOf(false)
    }
    var erroState by remember {
        mutableStateOf(false)
    }
    val callVideoById = RetrofitFactory().getVideoAulaService().getVideoById(idDoVideo.toInt())
    callVideoById.enqueue(object : Callback<ResultVideo> {
        override fun onResponse(p0: Call<ResultVideo>, p1: Response<ResultVideo>) {
            val videoResponse = p1.body()
            if (p1.isSuccessful) {

                dadosVideoAula = videoResponse?.video!![0]
                Log.i("CALMA", dadosVideoAula.toString())

                funcionouState = true

            } else {
                erroState = true
                Log.i("CALMA", videoResponse?.message!!.toString())
            }
        }

        override fun onFailure(p0: Call<ResultVideo>, p1: Throwable) {
            Log.i("ERRO_PERFIL", p1.toString())
            erroState = true
        }
    })
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

    }

    if (funcionouState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFC7E2FE))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(width = 130.dp, height = 60.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 16.dp)
            ) {
                val videoUri = Uri.parse(dadosVideoAula.url_video)
                PlayerVideo(videoUri)

            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFC7E2FE))

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                        Text(
                            text = dadosVideoAula.titulo,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.curtir),
                            contentDescription = "Favorito",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.salvar),
                            contentDescription = "Salvar",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            // Desenha a linha inferior
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color.White,
                                start = androidx.compose.ui.geometry.Offset(0f, y),
                                end = androidx.compose.ui.geometry.Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        rememberAsyncImagePainter(model = dadosVideoAula.id_professor),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 8.dp)
                    )//PRECISO MUDAR PRA FOTO DE PERFIL DO PROFESSOR E PRO NOME DO PROFESSOR
                    Text(
                        text = dadosVideoAula.id_professor.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = dadosVideoAula.titulo,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF2A3C78)
                    )
                    Text(
                        text = dadosVideoAula.titulo,
                        color = Color(0xFF2A3C78)
                    )
                }

                Spacer(modifier = Modifier.height(176.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            controleDeNavegacao.navigate("aulas?idModulo=${idModulo}&nomeModulo=${nomeModulo}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
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
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .background(
                                color = Color(0xFF89BFF7),
                                shape = MaterialTheme.shapes.medium
                            )
                    ) {
                        Text(
                            text = "Comentar...",
                            color = Color(0xFF456EDC),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "Enviar",
                        modifier = Modifier.size(24.dp).clickable{

                        }
                    )
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
                text = "mande uma\nmensagem para o\ntime de suporte",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

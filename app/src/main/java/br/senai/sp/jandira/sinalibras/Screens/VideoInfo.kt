//package br.senai.sp.jandira.sinalibras.Screens
//
//import android.net.Uri
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.drawBehind
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.media3.common.MediaItem
//import androidx.media3.common.util.UnstableApi
//import androidx.media3.exoplayer.ExoPlayer
//import androidx.media3.ui.PlayerView
//import androidx.navigation.NavHostController
//import br.senai.sp.jandira.sinalibras.R
//import br.senai.sp.jandira.sinalibras.model.Aluno
//import br.senai.sp.jandira.sinalibras.model.ResultAluno
//import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//@androidx.annotation.OptIn(UnstableApi::class)
//@OptIn(UnstableApi::class)
//@Composable
//fun PlayerVideo(videoUri: Uri) {
//    val context = LocalContext.current
//    val playerView = remember { PlayerView(context) }
//
//    val player = remember { ExoPlayer.Builder(context).build() }
//    playerView.player = player
//    val id = videoUri.toString().substringAfter("/d/").substringBefore("/view")
//    val downloadLink = "https://drive.google.com/uc?export=download&id=$id"
//
//    player.volume = 0f
//
//    val mediaItem = MediaItem.fromUri(Uri.parse(downloadLink))
//    player.setMediaItem(mediaItem)
//    player.prepare()
//    player.play()
//    LaunchedEffect(mediaItem) {
//        player.setMediaItem(mediaItem)
//        player.prepare()
//        player.play()
//    }
//
//    DisposableEffect(player) {
//        onDispose {
//            player.release()
//        }
//    }
//
//    AndroidView(factory = { playerView })
//}
//@Composable
//fun VideoInfo(controleDeNavegacao:NavHostController,id:String) {
//    var dadosVideoAula by remember {
//        mutableStateOf(Aluno())
//    }
//    var funcionouState by remember {
//        mutableStateOf(false)
//    }
//    val callVideoById = RetrofitFactory().getVideoAulaService().getVideoById(id)
//    callVideoById.enqueue(object : Callback<ResultVideo> {
//        override fun onResponse(p0: Call<ResultVideo>, p1: Response<ResultVideo>) {
//            val videoResponse = p1.body()
//            if (p1.isSuccessful) {
//
//                dadosVideoAula = videoResponse?.aluno!!
//                Log.i("CALMA",dadosVideoAula.toString())
//
//                funcionouState = true
//
//            } else {
//                Log.i("CALMA",videoResponse?.message!!.toString())
//            }
//        }
//
//        override fun onFailure(p0: Call<ResultVideo>, p1: Throwable) {
//            Log.i("ERRO_PERFIL", p1.toString())
//        }
//    })
//if (funcionouState){
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color(0xFFC7E2FE))
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "Logo",
//            modifier = Modifier
//                .size(width = 130.dp, height = 60.dp)
//                .align(Alignment.CenterHorizontally)
//                .padding(top = 16.dp)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .padding(horizontal = 16.dp)
//        ) {
//            val videoUri = Uri.parse(video)
//                PlayerVideo(videoUri)
//
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = Color(0xFFC7E2FE))
//
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "OI/OLÁ",
//                        style = MaterialTheme.typography.titleMedium,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Icon(
//                        painter = painterResource(id = R.drawable.seta),
//                        contentDescription = "Dropdown",
//                        modifier = Modifier
//                            .padding(start = 4.dp)
//                            .size(15.dp)
//                    )
//                }
//
//                Row(
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.heart),
//                        contentDescription = "Favorito",
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Icon(
//                        painter = painterResource(id = R.drawable.bookmark),
//                        contentDescription = "Salvar",
//                        modifier = Modifier.size(24.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .drawBehind {
//                        // Desenha a linha inferior
//                        val strokeWidth = 1.dp.toPx()  // Espessura da linha
//                        val y = size.height - strokeWidth / 2  // Posiciona no final da Row
//                        drawLine(
//                            color = Color.White,  // Cor da linha
//                            start = androidx.compose.ui.geometry.Offset(0f, y),
//                            end = androidx.compose.ui.geometry.Offset(size.width, y),
//                            strokeWidth = strokeWidth
//                        )
//                    }
//                    .padding(horizontal = 16.dp)
//                ,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.professor),
//                    contentDescription = "Avatar",
//                    modifier = Modifier
//                        .size(40.dp)
//                        .padding(end = 8.dp)
//                )
//                Text(
//                    text = "Professor Tal",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(text = "Smile05", fontWeight = FontWeight.SemiBold, color = Color(0xFF2A3C78))
//                Text(text = "-Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", color = Color(0xFF2A3C78))
//            }
//
//            Spacer(modifier = Modifier.height(176.dp))
//
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.arrow_back),
//                    contentDescription = "Voltar",
//                    modifier = Modifier.size(24.dp)
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .height(50.dp)
//                        .background(color = Color(0xFF89BFF7), shape = MaterialTheme.shapes.medium)
//                ) {
//                    Text(
//                        text = "Comentar...",
//                        color = Color(0xFF456EDC),
//                        style = MaterialTheme.typography.bodyMedium,
//                        modifier = Modifier
//                            .align(Alignment.CenterStart)
//                            .padding(start = 16.dp)
//                    )
//                }
//                Spacer(modifier = Modifier.width(16.dp))
//                Icon(
//                    painter = painterResource(id = R.drawable.send),
//                    contentDescription = "Enviar",
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//
//        }
//    }
//}
//    else{
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFD0E6FF))
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//
//        Image(
//            painter = painterResource(id = R.drawable.erro),
//            contentDescription = "logo",
//            modifier = Modifier
//
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//
//        Text(
//            text = "ERRO!!",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//
//        Text(
//            text = "mande uma\nmensagem para o\ntime de suporte",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Medium,
//            color = Color.Black,
//            textAlign = TextAlign.Center
//        )
//    }
//    }
//}

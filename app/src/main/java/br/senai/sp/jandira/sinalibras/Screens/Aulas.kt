package br.senai.sp.jandira.sinalibras.Screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.RespostaUsuario
import br.senai.sp.jandira.sinalibras.model.ResultVideo
import br.senai.sp.jandira.sinalibras.model.VideoAula
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Aulas(
    controleDeNavegacao: NavHostController,
    id: String,
    idModulo: String,
    tipoUsuario: String,
    fotoPerfil: String,
    nomeModulo: String
) {
    var dadosVideos by remember{
mutableStateOf(ResultVideo())
    }
    var funcionouState by remember {
        mutableStateOf(false)
    }
    var erroState by remember {
        mutableStateOf(false)
    }
    val callVideos =
        RetrofitFactory().getVideoAulaService().getVideoById(idModulo.toInt())

    callVideos.enqueue(object : Callback<ResultVideo> {
        override fun onResponse(p0: Call<ResultVideo>, p1: Response<ResultVideo>) {
            val videoResponse = p1.body()
            Log.i("ALUNO",videoResponse.toString())
            if (p1.isSuccessful) {
                if (videoResponse != null) {
                    if (videoResponse.video != null) {
                        funcionouState=true
                        dadosVideos= videoResponse
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
            Log.i("ERRO_VIDEO", p1.toString())
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
                .background(color = Color(0xFFC7E2FE))
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Button(
                    onClick = {
                        controleDeNavegacao.navigate("modulos?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
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
                    text = nomeModulo,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }
            LazyColumn {
                items(dadosVideos.video ?: emptyList()) { video ->
                    Log.i("CALMA", dadosVideos.toString())
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFFFFF)
                        ),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .clickable { controleDeNavegacao.navigate("video?idDoVideo=${video.id_videoaula}}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}&idModulo=${idModulo}&nomeModulo=${nomeModulo}") }
                    ) {
                        Column {
                            // Video thumbnail

                            Image(
                                rememberAsyncImagePainter(model = video.foto_capa),
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
                            Text(text = video.data, fontSize = 14.sp)
                        }
                    }
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

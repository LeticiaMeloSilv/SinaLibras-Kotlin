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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.Comentarios
import br.senai.sp.jandira.sinalibras.model.Postagem
import br.senai.sp.jandira.sinalibras.model.ResultComentario
import br.senai.sp.jandira.sinalibras.model.ResultPostagem
import br.senai.sp.jandira.sinalibras.model.ResultPostagemId
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import com.google.gson.Gson
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.format.DateTimeFormatter

@Composable
fun PostagemInfo(
    controleDeNavegacao: NavHostController,
    idDoVideo: String,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String
) {

    var salvarState by remember {
        mutableStateOf(false)
    }
    val currentDate: LocalDate = LocalDate.now()

    var dadosVideoAula by remember {
        mutableStateOf(Postagem())
    }
    var funcionouState by remember {
        mutableStateOf(false)
    }
    var erroState by remember {
        mutableStateOf(false)
    }
    var comentarioState = remember {
        mutableStateOf("")
    }

    var mensagemErroState = remember {
        mutableStateOf("")
    }
    Log.i("caralho", idDoVideo)
    val callVideoById = RetrofitFactory().getPostagensService().getPostagemById(idDoVideo.toInt())
    callVideoById.enqueue(object : Callback<ResultPostagemId> {
        override fun onResponse(p0: Call<ResultPostagemId>, p1: Response<ResultPostagemId>) {
            val videoResponse = p1.body()
            if (p1.isSuccessful) {

                dadosVideoAula = videoResponse?.postagem!![0]
                Log.i("TESTE", dadosVideoAula.toString())

                funcionouState = true

            } else {
                erroState = true
                Log.i("CALMA", videoResponse?.message!!.toString())
            }
        }

        override fun onFailure(p0: Call<ResultPostagemId>, p1: Throwable) {
            Log.i("ERRO_PERFIL", p1.toString())
            erroState = true
        }
    })

    val painter: Painter =
        if (dadosVideoAula.professor?.get(0)?.foto_perfil.isNullOrEmpty()) {
            Log.i("CALMA", "139")
            rememberAsyncImagePainter(model = dadosVideoAula.professor?.get(0)?.foto_perfil)
        } else {
            painterResource(id = R.drawable.perfil)
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

    }
    if (funcionouState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD0E6FF))
        ) {
        Column(modifier=Modifier.padding(6.dp)) {
            Image(
                painter = painterResource(id = R.drawable.logo_grande),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(width = 160.dp, height = 90.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(6.dp))
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
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painter,
                    contentDescription = "foto de perfil do professor",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = dadosVideoAula.professor?.get(0)?.nome.toString(),
                    color=Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 16.dp)
            ) {

                Image(
                    painter =  rememberAsyncImagePainter(
                        model = dadosVideoAula.foto_postagem,
                        error = painterResource(id = R.drawable.erro),
                        contentScale = ContentScale.Crop
                    ),
                    contentDescription = "foto da publicação",
                    modifier = Modifier
                        .fillMaxSize()
                )

            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                    Text(
                        text = dadosVideoAula.texto,
                        fontSize = 20.sp,
                        color=Color.Black
                    )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    val data = java.time.LocalDate.parse(dadosVideoAula.data.substring(0, 10))
                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                    val data_postado=data.format(formatter)
                    Text(
                        text = data_postado,
                        fontSize = 16.sp,
                        color=Color.Black

                    )
                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        val status=if(salvarState)
                            painterResource(id = R.drawable.salvo)
                        else{
                            painterResource(id = R.drawable.salvar)
                        }
                        Icon(
                            painter = status,
                            contentDescription = "Salvar",
                            modifier = Modifier.size(24.dp)
                                .clickable { salvarState=!salvarState },
                            tint = Color.Black,

                            )
                    }
                }


                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier.verticalScroll(scrollState).padding(vertical = 16.dp, horizontal = 6.dp).background(Color.Black)
                ){
                dadosVideoAula.comentarios?.forEach { comentario ->

                    Text(
                        text = comentario.id_aluno.toString(),//NOME ALUNO
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color(0xFF2A3C78)
                    )
                    Text(
                        text = comentario.comentario.toString(),//CONTEUDO DA MENS
                        color = Color(0xFF2A3C78),
                        fontSize = 12.sp,
                        modifier=Modifier.padding(horizontal = 8.dp)
                    )
                    }
                }
                Text(text = mensagemErroState.value, color = Color.Red)

            }
        }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color(0xFFC7E2FE))
                        .height(50.dp),
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
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    TextField(
                        value = comentarioState.value,
                        onValueChange = {
                            comentarioState.value=it
                        },
                        modifier = Modifier.width(200.dp),
                        label = { Text(text="Comentar...", color=Color(0xff334EAC)) },
                        colors = TextFieldDefaults
                            .colors(
                                focusedContainerColor = Color.Transparent,
                                focusedTextColor = Color(0xff334EAC),
                                unfocusedContainerColor = Color.Transparent,
                                unfocusedTextColor = Color(0xff334EAC),
                                errorTextColor = Color(0xff334EAC),
                                errorContainerColor = Color.Transparent,
                                errorPlaceholderColor = Color(0xff334EAC),
                                errorLabelColor = Color(0xff334EAC),
                                focusedPlaceholderColor = Color(0xff334EAC),
                                unfocusedPlaceholderColor = Color(0xff334EAC),
                                unfocusedLabelColor = Color(0xff334EAC),
                                focusedLabelColor = Color(0xff334EAC)
                            )
                    )
                    Button(
                        onClick = {
                            val callComentario = RetrofitFactory()
                                .getPostagensService().setSalvarComentarioPostagem(
                                    comentario = Comentarios(
                                        comentario = comentarioState.value,
                                        data = currentDate.toString(),
                                        id_postagem = idDoVideo.toLong(),
                                        id_aluno = id.toLong(),
                                    )
                                )
                            callComentario.enqueue(object : Callback<ResultComentario> {
                                override fun onResponse(
                                    p0: Call<ResultComentario>,
                                    p1: Response<ResultComentario>
                                ) {
                                    val comentarioSalvo = p1.body()

                                    if (p1.isSuccessful) {
                                        if (comentarioSalvo != null) {
                                            controleDeNavegacao.navigate("postagem?idDoVideo=${idDoVideo}&id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                        }
                                    } else {
                                        val errorBody = p1.errorBody()?.string()
                                        val gson = Gson()
                                        val usuarioSalvo = gson.fromJson(
                                            errorBody,
                                            ResultComentario::class.java
                                        )
                                        mensagemErroState.value = usuarioSalvo.message
                                    }
                                }


                                override fun onFailure(
                                    p0: Call<ResultComentario>,
                                    p1: Throwable
                                ) {
                                    Log.i("ERRO_CADASTRO", p1.toString())
                                    mensagemErroState.value =
                                        "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"

                                }

                            })

                        },
                        colors = ButtonColors(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent,
                            Color.Transparent
                        )
                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "Enviar",
                        modifier = Modifier.size(36.dp)
                    )
                }
                }

        }
    }
    else {
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
                ),
                modifier=Modifier.fillMaxWidth().align(Alignment.Start)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.btn_voltar),
                    contentDescription = "Botao Voltar",
                    modifier = Modifier.size(24.dp)
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

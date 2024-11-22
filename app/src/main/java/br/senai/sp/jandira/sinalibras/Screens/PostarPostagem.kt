package br.senai.sp.jandira.sinalibras.Screens

import android.net.Uri
import android.util.Log
import android.widget.VideoView
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.Postagem
import br.senai.sp.jandira.sinalibras.model.ResultPostagem
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PostPostagem(
//    controleDeNavegacao: NavHostController,
//    id: String,
//    nome: String,
//    email: String,
//    dataNascimento: String,
//    fotoPerfil: String,
//    tipoUsuario: String,
//
//    getContent: () -> Unit,
//    initialImageUri: Uri?
//) {
//    val currentDate: LocalDate = LocalDate.now()
//
//    var descricaoState = remember { mutableStateOf("") }
//    var tituloState = remember { mutableStateOf("") }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    LaunchedEffect(initialImageUri) {
//        imageUri = initialImageUri
//        imageUri?.let { uploadVideo(it) }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFD0E6FF))
//            .padding(10.dp),
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Button(
//                    onClick = {
//                        controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
//                    },
//                    colors = ButtonColors(
//                        Color.Transparent,
//                        Color.Transparent,
//                        Color.Transparent,
//                        Color.Transparent
//                    )
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.btn_voltar),
//                        contentDescription = "Botao Voltar",
//                        modifier = Modifier.size(20.dp)
//                    )
//                }
//
//
//                Image(
//                    painter = painterResource(id = R.drawable.logo_grande),
//                    contentDescription = "Logo",
//                    modifier = Modifier.size(100.dp)
//                )
//            }
//            if (imageUri != null) {
//                AndroidView(
//                    factory = { context ->
//                        VideoView(context).apply {
//                            setVideoURI(imageUri)
//                            start()
//                        }
//                    },
//                    modifier = Modifier
//                        .size(width = 240.dp, height = 170.dp)
//                )
//            } else {
//                Image(
//                    painter = painterResource(R.drawable.upload),
//                    contentDescription = "campo inserir video",
//                    modifier = Modifier
//                        .size(width = 240.dp, height = 170.dp)
//                        .clickable {
//                            getContent()
//                        },
//                    contentScale = ContentScale.Crop
//                )
//            }
//
//
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Text(
//                text = "Titulo do video:",
//
//                color = Color.Black,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.align(Alignment.Start)
//            )
//            OutlinedTextField(
//                value = tituloState.value,
//                onValueChange = { tituloState.value = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent,
//                    focusedTextColor = Color.Black,
//                    unfocusedTextColor = Color.Black
//                )
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Text(
//                text = "Descrição do video:",
//
//                color = Color.Black,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.align(Alignment.Start)
//            )
//            OutlinedTextField(
//                value = descricaoState.value,
//                onValueChange = { descricaoState.value = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp)
//                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent,
//                    focusedTextColor = Color.Black,
//                    unfocusedTextColor = Color.Black
//                )
//            )
//
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End
//            ) {
//                Button(
//                    onClick = {
//
//                        val callUsuarios = RetrofitFactory()
//                            .getPostagensService().setSalvarPostagem(
//                                postagem = Postagem(
//                                    texto = tituloState.value,
//                                    id_professor = id.toLong(),
//                                    url_video = link,
//                                    data = currentDate.toString()
//                                )
//                            )
//                        callUsuarios.enqueue(object : Callback<ResultPostagem> {
//                            override fun onResponse(
//                                p0: Call<ResultPostagem>,
//                                p1: Response<ResultPostagem>
//                            ) {
//                                val usuarioSalvo = p1.body()
//                                Log.i("aaaaa",usuarioSalvo.toString())
//
//                                if (p1.isSuccessful) {
//                                    if (usuarioSalvo != null) {
//                                        controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
//                                    }
//
//                                } else {
//                                    mensagemErroState.value = usuarioSalvo!!.message.toString()
//
//                                }
//                            }
//
//
//                            override fun onFailure(
//                                p0: Call<ResultPostagem>,
//                                p1: Throwable
//                            ) {
//                                Log.i("aaaaa", p1.toString())
//                                mensagemErroState.value =
//                                    "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"
//
//                            }
//
//                        })
//                    },
//
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF3B5EDF),
//                        contentColor = Color.White
//                    ),
//                    modifier = Modifier
//                        .width(127.dp)
//                        .height(43.dp),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(text = "POSTAR", fontWeight = FontWeight.Bold)
//                }
//            }
//        }
//    }
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostPostagem(
    controleDeNavegacao: NavHostController,
    id: String,
    tipoUsuario: String,
    fotoPerfil: String,
    dataNascimento: String,
    nome: String,
    email: String,
    getContent: () -> Unit,
    initialImageUri: Uri?
) {
    val currentDate: LocalDate = LocalDate.now()

    var mensagemErroState = remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var fotoState = remember { mutableStateOf("") }
    //var descricaoState = remember { mutableStateOf("") }
    var tituloState = remember { mutableStateOf("") }

    LaunchedEffect(initialImageUri) {
        imageUri = initialImageUri
        imageUri?.let { uploadFile(it) }
        fotoState.value = imageUri?.toString() ?: ""
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        controleDeNavegacao.navigate("perfil?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.btn_voltar),
                        contentDescription = "Botão Voltar",
                        modifier = Modifier.size(20.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.logo_grande),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Foto selecionada",
                    modifier = Modifier
                        .height(200.dp)
                        .clickable { getContent() },
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.upload),
                    contentDescription = "Campo inserir foto",
                    modifier = Modifier
                        .height(200.dp)
                        .clickable { getContent() },
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Conteúdo:",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(
                value = tituloState.value,
                onValueChange = { tituloState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black

                )
            )

            Spacer(modifier = Modifier.height(12.dp))

//            Text(
//                text = "Descrição da foto:",
//                color = Color.Black,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.align(Alignment.Start)
//            )
//            OutlinedTextField(
//                value = descricaoState.value,
//                onValueChange = { descricaoState.value = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp)
//                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    focusedBorderColor = Color.Transparent,
//                    unfocusedBorderColor = Color.Transparent,
//                    focusedTextColor = Color.Black,
//                    unfocusedTextColor = Color.Black
//                )
            //)

            Spacer(modifier = Modifier.height(100.dp))
            Text(text = mensagemErroState.value, color = Color.Red)
        }

        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD0E6FF)
            )
        ) {
            Button(
                onClick = {
                    val callUsuarios = RetrofitFactory()
                        .getPostagensService().setSalvarPostagem(
                            postagem = Postagem(
                                texto = tituloState.value,
                                foto_postagem = fotoState.value,
                                data = currentDate.toString(),
                                id_professor = id.toLong()
                            )
                        )
                    callUsuarios.enqueue(object : Callback<ResultPostagem> {
                        override fun onResponse(
                            call: Call<ResultPostagem>,
                            response: Response<ResultPostagem>
                        ) {
                            if (response.isSuccessful) {
                                controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                            } else {
                                mensagemErroState.value = "Erro ao salvar."
                            }
                        }

                        override fun onFailure(call: Call<ResultPostagem>, t: Throwable) {
                            Log.i("aaaaa", t.toString())

                            mensagemErroState.value =
                                "Ocorreu um erro, tente novamente mais tarde."
                        }
                    })
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B5EDF),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(135.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "POSTAR", fontWeight = FontWeight.Bold)
            }
        }
    }
}

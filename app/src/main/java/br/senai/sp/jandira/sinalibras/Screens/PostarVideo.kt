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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.ResultModulo
import br.senai.sp.jandira.sinalibras.model.ResultVideo
import br.senai.sp.jandira.sinalibras.model.VideoAula
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import org.threeten.bp.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostVideo(
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


    var mensagemErroState = remember {
        mutableStateOf("")
    }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var fotoState = remember { mutableStateOf("") }
    var descricaoState = remember { mutableStateOf("") }
    var tituloState = remember { mutableStateOf("") }
    var basicoState = remember { mutableStateOf(false) }
    var intermediarioState = remember { mutableStateOf(false) }
    var avancadoState = remember { mutableStateOf(false) }

    var moduloEscolhidoState = remember { mutableStateOf(false) }
    var idModuloEscolhidoState = remember { mutableStateOf("") }

    var idNivelEscolhidoState = remember { mutableStateOf("") }


    var dadosModulos by remember {
        mutableStateOf(ResultModulo())
    }
    LaunchedEffect(initialImageUri) {
        imageUri = initialImageUri
        imageUri?.let { uploadVideo(it) }
        fotoState.value = imageUri.toString()

    }


    val callModulos = RetrofitFactory().getVideoAulaService().getAllModulos()
    callModulos.enqueue(object : Callback<ResultModulo> {
        override fun onResponse(p0: Call<ResultModulo>, p1: Response<ResultModulo>) {
            val alunoResponse = p1.body()
            if (alunoResponse == null) {
                Log.i("ERRO_MODULOS", p1.toString())
            } else {
                Log.i("TAG", alunoResponse.toString())
                //funcionouState = true
                dadosModulos = alunoResponse
            }
        }

        override fun onFailure(p0: Call<ResultModulo>, p1: Throwable) {
            Log.i("ERRO_PERFIL", p1.toString())
        }


    })

    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD0E6FF))
            .padding(10.dp),
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
                    painter = painterResource(id = R.drawable.logo_grande),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )
            }

            if (imageUri != null) {
                AndroidView(
                    factory = { context ->
                        VideoView(context).apply {
                            setVideoURI(imageUri)
                            start()
                        }
                    },
                    modifier = Modifier
                        .size(width = 240.dp, height = 170.dp)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.upload),
                    contentDescription = "campo inserir video",
                    modifier = Modifier
                        .size(width = 240.dp, height = 170.dp)
                        .clickable {
                            getContent()
                        },
                    contentScale = ContentScale.Crop
                )
            }



            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Titulo do video:",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(
                value = tituloState.value,
                onValueChange = { tituloState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Descrição do video:",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start)
            )
            OutlinedTextField(
                value = descricaoState.value,
                onValueChange = { descricaoState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
                    .background(Color(0xFFE2EEFF), shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Nível",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = basicoState.value,
                    onCheckedChange = {
                        intermediarioState.value = false
                        avancadoState.value = false
                        basicoState.value = true
                        idNivelEscolhidoState.value = ""
                        idNivelEscolhidoState.value = "0"
                    },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
                )
                Text(text = "Básico", color = Color.Black)

                Checkbox(
                    checked = intermediarioState.value,
                    onCheckedChange = {
                        intermediarioState.value = true
                        avancadoState.value = false
                        basicoState.value = false
                        idNivelEscolhidoState.value = ""
                        idNivelEscolhidoState.value = "1"

                    },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
                )
                Text(text = "Intermediário", color = Color.Black)
                Checkbox(
                    checked = avancadoState.value,
                    onCheckedChange = {
                        intermediarioState.value = false
                        avancadoState.value = true
                        basicoState.value = false
                        idNivelEscolhidoState.value = ""

                        idNivelEscolhidoState.value = "2"

                    },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
                )
                Text(text = "Avançado", color = Color.Black)
            }

            Text(
                text = "Módulo",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                dadosModulos.modulo?.forEach { item ->
                    Row {
                        Checkbox(
                            checked = moduloEscolhidoState.value,
                            onCheckedChange = {
                                if (moduloEscolhidoState.value) {
                                    idModuloEscolhidoState.value = ""
                                } else {
                                    moduloEscolhidoState.value = true
                                    idModuloEscolhidoState.value = item.id_modulo.toString()
                                }

                            },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF3B5EDF))
                        )
                        Text(text = item.modulo, color = Color.Black)

                    }
                }

            }

            Spacer(modifier = Modifier.height(100.dp))
            Text(text = mensagemErroState.value, color = Color.Red)
        }
        Card(
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),

            colors = CardColors(
                containerColor = Color(0xFFD0E6FF),
                contentColor = Color(0xFFD0E6FF),
                disabledContentColor = Color(0xFFD0E6FF),
                disabledContainerColor = Color(0xFFD0E6FF)
            )
        ) {
            Button(
                onClick = {
                    val callUsuarios = RetrofitFactory()
                        .getVideoAulaService().setSalvarVideoAula(
                            videoAula = VideoAula(
                                titulo = tituloState.value,
                                duracao = "00:00:00",
                                foto_capa = "link",
                                id_modulo = idModuloEscolhidoState.value.toInt(),
                                id_nivel = idNivelEscolhidoState.value.toInt(),
                                id_professor = id.toLong(),
                                url_video = "https://firebasestorage.googleapis.com/v0/b/sinalibras-439801.appspot.com/o/lv_0_20241118103324.mp4?alt=media&token=7ceaf70a-5f26-4535-9aed-253431c60346",
                                data_cadastro = currentDate.toString()
                            )
                        )
                    callUsuarios.enqueue(object : Callback<ResultVideo> {
                        override fun onResponse(
                            p0: Call<ResultVideo>,
                            p1: Response<ResultVideo>
                        ) {
                            val usuarioSalvo = p1.body()

                            if (p1.isSuccessful) {
                                if (usuarioSalvo != null) {
                                    controleDeNavegacao.navigate("feed?id=${id}&tipoUsuario=${tipoUsuario}&fotoPerfil=${fotoPerfil}")
                                }

                            } else {

                                if (usuarioSalvo != null) {
                                    mensagemErroState.value = usuarioSalvo.message.toString()
                                }
                            }
                        }


                        override fun onFailure(
                            p0: Call<ResultVideo>,
                            p1: Throwable
                        ) {
                            Log.i("ERRO_CADASTRO", p1.toString())
                            mensagemErroState.value =
                                "Ocorreu um erro, o serviço pode estar indisponivel.Favor, tente novamente mais tarde"

                        }

                    })
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3B5EDF),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(127.dp)
                    .height(43.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "POSTAR", fontWeight = FontWeight.Bold)
            }
        }

    }
}


fun uploadVideo(fileUri: Uri) {
    val storageRef: StorageReference = FirebaseStorage.getInstance().reference
    val fileRef = storageRef.child("videos/${fileUri.lastPathSegment}")
    fileRef.putFile(fileUri)
        .addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener { uri ->
                Log.i("UPLOAD", "URL de download: $uri")
                link = uri.toString()
            }
        }
        .addOnFailureListener { exception ->
            link = ""
            Log.i("UPLOAD", "Falha no upload: ${exception.message}")
        }
}

package br.senai.sp.jandira.sinalibras.Screens

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import br.senai.sp.jandira.sinalibras.model.Aluno
import br.senai.sp.jandira.sinalibras.model.Professor
import br.senai.sp.jandira.sinalibras.model.ResultAluno
import br.senai.sp.jandira.sinalibras.model.ResultProfessor
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import coil.compose.rememberAsyncImagePainter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

var link = ""

@Composable
fun EditarPerfil(
    controleDeNavegacao: NavHostController,
    id: String,
    email: String,
    nome: String,
    dataNascimento: String,
    fotoPerfil: String,
    tipoUsuario: String,
    getContent: () -> Unit,
    initialImageUri: Uri?
) {

    val data_nascimento = dataNascimento.take(10)
    val data = data_nascimento.split("-")
    val dia = data[2]
    val mes = data[1]
    val ano = data[0]
    val dataFormatada = "$dia/$mes/$ano"

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var nomeState = remember {
        mutableStateOf("")
    }
    var emailState = remember {
        mutableStateOf(email)
    }
    var nascimentoState = remember {
        mutableStateOf(dataFormatada)
    }

    var fotoState = remember { mutableStateOf("") }

    var umError = remember {
        mutableStateOf(false)
    }
    var mensagemErroState = remember {
        mutableStateOf("")
    }

    LaunchedEffect(initialImageUri) {
        imageUri = initialImageUri
        imageUri?.let { uploadFile(it) }
        fotoState.value = imageUri.toString()
    }


    val painter: Painter =
        if (imageUri != null) {

            rememberAsyncImagePainter(
                model = imageUri,
                placeholder = painterResource(id = R.drawable.perfil),
                error = painterResource(id = R.drawable.erro)
            )
        } else if (fotoPerfil != "" && fotoPerfil != "null" && fotoPerfil.isNotEmpty()) {
            rememberAsyncImagePainter(
                model = fotoPerfil,
                placeholder = painterResource(id = R.drawable.perfil),
                error = painterResource(id = R.drawable.erro)
            )
        } else {
            painterResource(id = R.drawable.perfil)
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E2FE))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)

        ) {

            Button(
                onClick = {
                    controleDeNavegacao.navigate("configuracoes?id=${id}&email=${email}&nome=${nome}&dataNascimento=${dataNascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}")
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

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Editar Perfil",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .align(Alignment.TopCenter)
            )
        }


        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFF58B86E), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
//
//            AsyncImage(
//                model = selectedImageUri ?: painter,
//                contentDescription = "Selected image",
//                modifier = Modifier
//                        .size(width = 200.dp, height = 200.dp)
//                        .clickable {
//                            intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                            launcher.launch(intent)
//                        }
//            )
            Image(

                painter = painter,
                contentDescription = "foto de Perfil",
                modifier = Modifier
                    .size(width = 170.dp, height = 170.dp)
                    .clickable {
                        getContent()
                    }
                    .clip(CircleShape), // Aplica a forma circular
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color(0xFF3F51B5), shape = CircleShape)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nomeState.value,
            onValueChange = { nomeState.value = it },
            label = { Text("Nome", color = Color.Black) },
            isError = umError.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                errorTextColor = Color.Red,
                textSelectionColors = TextSelectionColors(Color.Black, Color.Black),

                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                disabledLabelColor = Color.Black,
                errorLabelColor = Color.Red,

                focusedContainerColor = Color.Unspecified,
                unfocusedContainerColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                errorContainerColor = Color.Unspecified,

                cursorColor = Color.Black,
                errorCursorColor = Color.Unspecified,

                focusedIndicatorColor = Color(0xff334EAC),
                unfocusedIndicatorColor = Color(0xff334EAC),
                disabledIndicatorColor = Color(0xff334EAC),
                errorIndicatorColor = Color.Red,

                focusedLeadingIconColor = Color.Unspecified,
                unfocusedLeadingIconColor = Color.Unspecified,
                disabledLeadingIconColor = Color.Unspecified,
                errorLeadingIconColor = Color.Red,

                focusedTrailingIconColor = Color.Unspecified,
                unfocusedTrailingIconColor = Color.Unspecified,
                disabledTrailingIconColor = Color.Unspecified,
                errorTrailingIconColor = Color.Red,

                focusedPlaceholderColor = Color.Unspecified,
                unfocusedPlaceholderColor = Color.Unspecified,
                disabledPlaceholderColor = Color.Unspecified,
                errorPlaceholderColor = Color.Red,

                focusedSupportingTextColor = Color.Unspecified,
                unfocusedSupportingTextColor = Color.Unspecified,
                disabledSupportingTextColor = Color.Unspecified,
                errorSupportingTextColor = Color.Red,

                focusedPrefixColor = Color.Unspecified,
                unfocusedPrefixColor = Color.Unspecified,
                disabledPrefixColor = Color.Unspecified,
                errorPrefixColor = Color.Red,

                focusedSuffixColor = Color.Unspecified,
                unfocusedSuffixColor = Color.Unspecified,
                disabledSuffixColor = Color.Unspecified,
                errorSuffixColor = Color.Red
            )
        )

        OutlinedTextField(
            value = nascimentoState.value,
            onValueChange = { nascimentoState.value = it },
            isError = umError.value,
            label = { Text("Data de Nascimento") },
            trailingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = "Selecionar data")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                errorTextColor = Color.Red,
                textSelectionColors = TextSelectionColors(Color.Black, Color.Black),

                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                disabledLabelColor = Color.Black,
                errorLabelColor = Color.Red,

                focusedContainerColor = Color.Unspecified,
                unfocusedContainerColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                errorContainerColor = Color.Unspecified,

                cursorColor = Color.Black,
                errorCursorColor = Color.Unspecified,

                focusedIndicatorColor = Color(0xff334EAC),
                unfocusedIndicatorColor = Color(0xff334EAC),
                disabledIndicatorColor = Color(0xff334EAC),
                errorIndicatorColor = Color.Red,

                focusedLeadingIconColor = Color.Unspecified,
                unfocusedLeadingIconColor = Color.Unspecified,
                disabledLeadingIconColor = Color.Unspecified,
                errorLeadingIconColor = Color.Red,

                focusedTrailingIconColor = Color.Unspecified,
                unfocusedTrailingIconColor = Color.Unspecified,
                disabledTrailingIconColor = Color.Unspecified,
                errorTrailingIconColor = Color.Red,

                focusedPlaceholderColor = Color.Unspecified,
                unfocusedPlaceholderColor = Color.Unspecified,
                disabledPlaceholderColor = Color.Unspecified,
                errorPlaceholderColor = Color.Red,

                focusedSupportingTextColor = Color.Unspecified,
                unfocusedSupportingTextColor = Color.Unspecified,
                disabledSupportingTextColor = Color.Unspecified,
                errorSupportingTextColor = Color.Red,

                focusedPrefixColor = Color.Unspecified,
                unfocusedPrefixColor = Color.Unspecified,
                disabledPrefixColor = Color.Unspecified,
                errorPrefixColor = Color.Red,

                focusedSuffixColor = Color.Unspecified,
                unfocusedSuffixColor = Color.Unspecified,
                disabledSuffixColor = Color.Unspecified,
                errorSuffixColor = Color.Red
            )
        )

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            isError = umError.value,
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                errorTextColor = Color.Red,
                textSelectionColors = TextSelectionColors(Color.Black, Color.Black),

                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                disabledLabelColor = Color.Black,
                errorLabelColor = Color.Red,

                focusedContainerColor = Color.Unspecified,
                unfocusedContainerColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                errorContainerColor = Color.Unspecified,

                cursorColor = Color.Black,
                errorCursorColor = Color.Unspecified,

                focusedIndicatorColor = Color(0xff334EAC),
                unfocusedIndicatorColor = Color(0xff334EAC),
                disabledIndicatorColor = Color(0xff334EAC),
                errorIndicatorColor = Color.Red,

                focusedLeadingIconColor = Color.Unspecified,
                unfocusedLeadingIconColor = Color.Unspecified,
                disabledLeadingIconColor = Color.Unspecified,
                errorLeadingIconColor = Color.Red,

                focusedTrailingIconColor = Color.Unspecified,
                unfocusedTrailingIconColor = Color.Unspecified,
                disabledTrailingIconColor = Color.Unspecified,
                errorTrailingIconColor = Color.Red,

                focusedPlaceholderColor = Color.Unspecified,
                unfocusedPlaceholderColor = Color.Unspecified,
                disabledPlaceholderColor = Color.Unspecified,
                errorPlaceholderColor = Color.Red,

                focusedSupportingTextColor = Color.Unspecified,
                unfocusedSupportingTextColor = Color.Unspecified,
                disabledSupportingTextColor = Color.Unspecified,
                errorSupportingTextColor = Color.Red,

                focusedPrefixColor = Color.Unspecified,
                unfocusedPrefixColor = Color.Unspecified,
                disabledPrefixColor = Color.Unspecified,
                errorPrefixColor = Color.Red,

                focusedSuffixColor = Color.Unspecified,
                unfocusedSuffixColor = Color.Unspecified,
                disabledSuffixColor = Color.Unspecified,
                errorSuffixColor = Color.Red
            )
        )

        Card(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(0xff334EAC),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(Color.Transparent)
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { controleDeNavegacao.navigate("editarSenha?id=${id}&email=${email}&nome=${nome}&dataNascimento=${dataNascimento}&fotoPerfil=${fotoPerfil}&tipoUsuario=${tipoUsuario}") },
            shape = RoundedCornerShape(12.dp),


            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Alterar Senha", fontSize = 16.sp, fontWeight = FontWeight.Medium,color=Color.Black )
                Icon(Icons.Default.ArrowForward, contentDescription = "Alterar Senha")
            }
        }

        Spacer(modifier = Modifier.height(200.dp))

        Text(text = mensagemErroState.value, color = Color.Red)

        Button(
            onClick = {
                val partesDataMandar = nascimentoState.value.split("/")
                val diaMandar = partesDataMandar[0]
                val mesMandar = partesDataMandar[1]
                val anoMandar = partesDataMandar[2]
                val dataNascimentoMandar = "$anoMandar-$mesMandar-$diaMandar"

                Log.i( "calma",nomeState.value.toString())
                Log.i( "calma",emailState.value.toString())
                Log.i( "calma",dataNascimentoMandar.toString())
                Log.i( "calma",link.toString())


                if (nomeState.value == "" || emailState.value == "" || nascimentoState.value == "") {
                    mensagemErroState.value =
                        "Todos os campos devem estar preenchidos"
                    umError.value = true
                } else if (nascimentoState.value.length > 10 || nascimentoState.value.length > 10) {
                    mensagemErroState.value =
                        "O formato do campo data de nascimento está incorreto"
                    umError.value = true
                } else {
                    if (tipoUsuario == "aluno") {
                        val callAtualizarAluno =
                            RetrofitFactory().getUsuarioService().setAtualizarAluno(
                                id.toInt(),
                                usuario = Aluno(
                                    nome = nomeState.value,
                                    email = emailState.value.lowercase(),
                                    data_nascimento = dataNascimentoMandar,
                                    foto_perfil = link,
                                    id_aluno=id.toLong()

                                )
                            )
                        callAtualizarAluno.enqueue(object : Callback<ResultAluno> {
                            override fun onResponse(
                                p0: Call<ResultAluno>,
                                p1: Response<ResultAluno>
                            ) {
                                val alunoResponse = p1.body()
                                Log.i("ALUNO", alunoResponse.toString())
                                if (p1.isSuccessful) {
                                    if (alunoResponse != null) {
                                        controleDeNavegacao.navigate(
                                            "configuracoes?id=${alunoResponse.aluno?.id_aluno}&email=${alunoResponse.aluno?.email}&nome=${alunoResponse.aluno?.nome}&dataNascimento=${alunoResponse.aluno?.data_nascimento}&fotoPerfil=${alunoResponse.aluno?.foto_perfil}&tipoUsuario=${tipoUsuario}"
                                        )
                                    }

                                } else {
                                    Log.i("CALMA", alunoResponse?.message!!.toString())
                                }
                            }

                            override fun onFailure(p0: Call<ResultAluno>, p1: Throwable) {
                                Log.i("ERRO_EDITAR_PERFIL", p1.toString())
                            }
                        })
                    } else {
                        val callAtualizarProfessor =
                            RetrofitFactory().getUsuarioService().setAtualizarProfessor(
                                id.toInt(),
                                usuario = Professor(
                                    nome = nomeState.value,
                                    email = emailState.value.lowercase(),
                                    data_nascimento = dataNascimentoMandar,
                                    foto_perfil = link,
                                    id_professor=id.toLong()
                                )
                            )
                        callAtualizarProfessor.enqueue(object : Callback<ResultProfessor> {
                            override fun onResponse(
                                p0: Call<ResultProfessor>,
                                p1: Response<ResultProfessor>
                            ) {

                                val professorResponse = p1.body()
                                Log.i("calma",professorResponse.toString())
                                if (p1.isSuccessful) {
                                    if (professorResponse != null) {
                                        controleDeNavegacao.navigate("configuracoes?id=${professorResponse.professor?.id_professor}&email=${professorResponse.professor?.email}&nome=${professorResponse.professor?.nome}&dataNascimento=${professorResponse.professor?.data_nascimento}&fotoPerfil=${professorResponse.professor?.foto_perfil}&tipoUsuario=${tipoUsuario}")
                                    }
                                    else{
                                    Log.i("Calma", professorResponse?.message.toString())}

                                } else {
                                    Log.i("CALMA", professorResponse?.message!!.toString())
                                }
                            }
                            override fun onFailure(p0: Call<ResultProfessor>, p1: Throwable) {
                                Log.i("ERRO_EDITAR_PERFIL", p1.toString())
                            }
                        })
                    }
                }
            },
            modifier = Modifier
                .width(412.dp)
                .height(56.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Salvar Alterações",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

}
//fun uploadFile(fileUri: Uri, storageRef: StorageReference) {
//    val fileRef = storageRef.child("uploads/${fileUri.lastPathSegment}")
//    fileRef.putFile(fileUri)
//        .addOnSuccessListener {
//            fileRef.downloadUrl.addOnSuccessListener { uri ->
//                val downloadUrl = uri.toString()
//                println("Upload bem-sucedido! Link de download: $downloadUrl")        }
//        }
//        .addOnFailureListener {
//            println("Falha no upload: ${it.message}")
//        }
//}

fun uploadFile(fileUri: Uri) {
    val storageRef: StorageReference = FirebaseStorage.getInstance().reference
    val fileRef = storageRef.child("uploads/${fileUri.lastPathSegment}")
    fileRef.putFile(fileUri)
        .addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener { uri ->
                Log.i("CALMA", "URL de download: $uri")
                link = uri.toString()
            }
        }
        .addOnFailureListener { exception ->
            link = ""
            Log.i("CALMA", "Falha no upload: ${exception.message}")
        }
}
package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.activity.result.launch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.Usuario
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Perfil(controleDeNavegacao: NavHostController,id:Int) {
    Log.i("OUTRO ID", id.toString())
    val dadosPerfil=remember{
        mutableStateOf(Usuario())
    }
val callCharacterById=RetrofitFactory().getUsuarioService().getAlunoId(id)
    callCharacterById.enqueue(object :Callback<Usuario> {
        override fun onResponse(p0: Call<Usuario>, p1: Response<Usuario>) {
            Log.i("AAAAAAAA",p1.toString())
//dadosPerfil=p1.body()
        }

        override fun onFailure(p0: Call<Usuario>, p1: Throwable) {
            Log.i("AAAAAAAA",p1.toString())
        }


    })
    Log.i("AAAAAAAA",id.toString())

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFC7E2FE))
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(330.dp)
        ) {

            Icon(modifier = Modifier.padding(vertical = 7.dp),
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "BackArrow",
                tint = Color.Black)

            Column {
                Box( modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.TopCenter),
                        text = "Perfil",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 26.sp
                    )

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "perfil",
                        modifier = Modifier
                            .size(width = 170.dp, height = 170.dp)
                            .align(Alignment.Center)
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 45.dp),
                        text = "James Smith",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 26.sp
                    )

                    Image(
                        painter = painterResource(id = R.drawable.aluno),
                        contentDescription = "tag",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .size(height = 33.dp, width = 151.dp)
                    )

                }
            }





        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(330.dp)
        ){

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)) // Cor de fundo azul claro
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ícone de "like"
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "perfil",
                        modifier = Modifier
                            .size(width = 60.dp, height = 70.dp)

                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        // Título
                        Text(
                            text = "NÚMEROS",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Botões de Vídeos e Atividades
                        Row {
                            Button(
                                onClick = { /* TODO: Ação dos Vídeos */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0x00FFFFFF)),
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .border(
                                        border = BorderStroke(2.dp, Color(0xFF345ADE)),
                                        shape = RoundedCornerShape(16.dp)
                                    ),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "4 Vídeos",
                                    color = Color(0xFF345ADE),
                                    fontSize = 12.sp
                                )
                            }

                            Button(
                                modifier = Modifier.border(border = BorderStroke(2.dp, Color(0xFFFEC608)),
                                    shape = RoundedCornerShape(16.dp)),
                                onClick = { /* TODO: Ação das Atividades */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(
                                    0x00FFFFFF
                                )
                                ),

                                contentPadding = PaddingValues(horizontal = 5.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "2 Atividades",
                                    color = Color(0xFFFEC608),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Gráfico Circular (ProgressIndicator)
                    Box(
                        contentAlignment = Alignment.Center, // Centraliza o conteúdo dentro do Box
                        modifier = Modifier.size(48.dp) // Define o tamanho do Box
                    ) {
                        // Gráfico Circular (ProgressIndicator)
                        CircularProgressIndicator(
                            progress = 0.65f, // 65% de progresso
                            modifier = Modifier.fillMaxSize(), // Faz o CircularProgressIndicator ocupar todo o Box
                            color = Color(0xFF4CAF50), // Cor verde
                            strokeWidth = 6.dp
                        )

                        // Texto com percentual
                        Text(
                            text = "65%",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }

    }


}
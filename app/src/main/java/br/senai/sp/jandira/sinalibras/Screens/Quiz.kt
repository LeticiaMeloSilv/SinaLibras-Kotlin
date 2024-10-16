package br.senai.sp.jandira.sinalibras.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.sinalibras.R
import br.senai.sp.jandira.sinalibras.model.ResultQuiz
import br.senai.sp.jandira.sinalibras.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Quiz(controleDeNavegacao: NavHostController,email:String) {
                //controleDeNavegacao.navigate("acerto/${70}")
    var dadosPerfil by remember {
        mutableStateOf(ResultQuiz())
    }
    var funcionouState by remember {
        mutableStateOf(false)
    }
    val callQuestions = RetrofitFactory().getQuizService().getAllQuestoes()
    callQuestions.enqueue(object : Callback<ResultQuiz> {
        override fun onResponse(p0: Call<ResultQuiz>, p1: Response<ResultQuiz>) {
            val alunoResponse = p1.body()
            Log.i("TAG", alunoResponse.toString())
            funcionouState=true
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
    if(funcionouState){
    Column(
        modifier = Modifier
            .background(brush = gradientBrush)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(width = 200.dp, height = 130.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
            )

            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.White)
                    .height(510.dp)
                    .fillMaxWidth()

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(0.8f)
                                .width(258.dp)
                                .height(160.dp),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Text(
                                text = "Video com palavra difícil",
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        Text(
                            text = "O sinal mostrado acima significa casa?",
                            modifier = Modifier.padding(top = 8.dp),
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Card(
                                    modifier = Modifier
                                        .size(100.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                                ) {
                                    Text(text="")
                                }

                                Card(
                                    modifier = Modifier
                                        .size(100.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFF8C8CF7))
                                ) {
                                    Text(text="")
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Card(
                                    modifier = Modifier
                                        .size(100.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                                ) {
                                    Text(text="")
                                }

                                Card(
                                    modifier = Modifier
                                        .size(100.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                                ) {
                                    Text(text="")
                                }
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = { /* Ação do botão */ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .width(298.dp)
                .height(54.dp),
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

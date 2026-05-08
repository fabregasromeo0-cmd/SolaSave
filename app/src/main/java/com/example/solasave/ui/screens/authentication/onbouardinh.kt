package com.example.solasave.ui.screens.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.solasave.ui.navigation.ROUTES

@Composable
fun OnboardingScreen(navController: NavHostController, modifier: Modifier)
{
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    HorizontalPager(state = pagerState) {
        AsyncImage(
            model = "https://images.pexels.com/photos/12663447/pexels-photo-12663447.jpeg?_gl=1*19nq08e*_ga*NTUwNjUzODQzLjE3Njk0Mjk1MzQ.*_ga_8JE65Q40S6*czE3Nzc5ODIxNTUkbzExJGcxJHQxNzc3OTgyMTg3JGoyOCRsMCRoMA..",
            contentDescription = null,
        )
        AsyncImage(
            model = "https://images.pexels.com/photos/6876536/pexels-photo-6876536.jpeg?_gl=1*qatg1l*_ga*NTUwNjUzODQzLjE3Njk0Mjk1MzQ.*_ga_8JE65Q40S6*czE3Nzc5ODIxNTUkbzExJGcxJHQxNzc3OTgyNjg2JGoyNCRsMCRoMA..",
            contentDescription = null,
        )
        AsyncImage(
            model = "https://images.pexels.com/photos/18316987/pexels-photo-18316987.jpeg?_gl=1*3wu5y3*_ga*NTUwNjUzODQzLjE3Njk0Mjk1MzQ.*_ga_8JE65Q40S6*czE3Nzc5ODIxNTUkbzExJGcxJHQxNzc3OTgyNjY2JGo0NCRsMCRoMA..",
            contentDescription = null,
        )
    }
    Row (modifier = Modifier,
        horizontalArrangement = Arrangement.Center){
        OutlinedButton(onClick = { ROUTES.Login },
            modifier = Modifier.size(width = 70.dp,
                height = 50.dp)) {
            Text(
                text = "Already have an account?"
            )
        }
        OutlinedButton(onClick = { ROUTES.Signup },
            modifier = Modifier.size(width = 50.dp, height = 40.dp)) {
            Text(
                text = "Create new account"
            )
        }
    }


}
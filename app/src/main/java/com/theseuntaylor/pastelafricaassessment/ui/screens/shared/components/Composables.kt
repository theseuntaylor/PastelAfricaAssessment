package com.theseuntaylor.pastelafricaassessment.ui.screens.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.theseuntaylor.pastelafricaassessment.data.model.Article
import com.theseuntaylor.pastelafricaassessment.ui.theme.PastelAfricaAssessmentTheme
import com.theseuntaylor.pastelafricaassessment.ui.utils.newsList

@Composable
fun NewsTile(news: Article, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp)
            .padding(bottom = 7.dp)
            .height(IntrinsicSize.Min)
            .clickable(
                onClick = onClick
            )
    ) {

        // ImageView
        val image: Painter = rememberAsyncImagePainter(news.urlToImage)

        Image(
            image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillWidth
        )

        // Title
        Column(
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text(
                text = news.description ?: "No description!",
                fontSize = 14.sp,
                fontWeight = Bold,
                color = White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = news.author ?: "No author name!",
                fontSize = 12.sp,
                color = White,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 10.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PastelAfricaAssessmentTheme {
        val dummyResponse = newsList[0]
        NewsTile(dummyResponse, onClick = {
            print( "This box was clicked!")
        })
    }
}

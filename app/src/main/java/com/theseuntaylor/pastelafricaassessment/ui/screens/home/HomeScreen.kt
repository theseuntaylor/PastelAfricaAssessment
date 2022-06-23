package com.theseuntaylor.pastelafricaassessment.ui.screens.home

import com.theseuntaylor.pastelafricaassessment.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.theseuntaylor.pastelafricaassessment.ui.screens.shared.components.NewsTile
import com.theseuntaylor.pastelafricaassessment.ui.screens.shared.view_models.NewsViewModel
import com.theseuntaylor.pastelafricaassessment.ui.utils.Const.COUNTRY

@Composable
fun ShowNewsList(viewModel: NewsViewModel = viewModel()) {

    val uiState = viewModel.uiState
    val context = LocalContext.current

    when (val state = uiState.value) {

        is NewsViewModel.NewsUiState.Failure -> {
            Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { viewModel.getNews(COUNTRY) },
                            modifier = Modifier
                        ) {
                            Text(
                                text = "Retry"
                            )
                        }
                    }
                }
                items(state.data) { news ->
                    NewsTile(news, onClick = {
                        Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                    })
                }
            }
        }

        is NewsViewModel.NewsUiState.Success -> {
            LazyColumn {
                items(state.data) { news ->
                    NewsTile(news, onClick = {
                        news.url.let { openTab(context, it) }
                    })
                }
            }
        }

        is NewsViewModel.NewsUiState.Loading -> if (state.isLoading) ShowLoader()

    }

}

@Composable
fun ShowLoader() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

fun openTab(context: Context, newsUrl: String) {

    try {
        val packageName = "com.android.chrome"

        val activity = (context as? Activity)

        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.setInstantAppsEnabled(true)

        val params = CustomTabColorSchemeParams.Builder()
            .setNavigationBarColor(
                ContextCompat.getColor(context, R.color.purple_200)
            ).build()
        builder.setDefaultColorSchemeParams(params)

        val customBuilder = builder.build()

        if (packageName != null) {
            customBuilder.intent.setPackage(packageName)
            customBuilder.launchUrl(context, Uri.parse(newsUrl))
        } else {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl))

            activity?.startActivity(i)
        }
    } catch (ex: Exception) {
        ex.localizedMessage
        Toast.makeText(
            context,
            "We experienced some issues opening a chrome tab on your device! Please, ensure Google Chrome is installed on your device!",
            Toast.LENGTH_LONG
        ).show()
    }
}
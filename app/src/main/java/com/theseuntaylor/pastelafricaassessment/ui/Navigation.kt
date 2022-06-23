package com.theseuntaylor.pastelafricaassessment.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theseuntaylor.pastelafricaassessment.ui.AppDestinations.NEWS_LIST_ROUTE
import com.theseuntaylor.pastelafricaassessment.ui.screens.home.ShowNewsList

// route names
object AppDestinations{
    const val NEWS_LIST_ROUTE = "showNews"
}

@Composable
fun NewsApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NEWS_LIST_ROUTE) {

        composable(route = NEWS_LIST_ROUTE) {
            ShowNewsList()
        }

    }
}
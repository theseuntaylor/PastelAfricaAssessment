package com.theseuntaylor.pastelafricaassessment.ui.screens.shared.view_models

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.theseuntaylor.pastelafricaassessment.data.NewsRepository
import com.theseuntaylor.pastelafricaassessment.data.model.Article
import com.theseuntaylor.pastelafricaassessment.ui.utils.Const.COUNTRY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsRepository(application)



    private val _uiState = mutableStateOf<NewsUiState>(NewsUiState.Loading())
    val uiState: State<NewsUiState> = _uiState


    init {
        viewModelScope.launch {
            getNews(COUNTRY)
        }
    }

    fun getNews(country: String) {


        viewModelScope.launch(Dispatchers.IO) {

            var news = repository.getNewsOnce()

            try {
                _uiState.value = NewsUiState.Loading()

                val response = repository.getNewsList(country)

                if (response.status == "ok") {

                    news = response.articles

                    repository.deleteNews()
                    repository.addNews(news)

                    _uiState.value = NewsUiState.Success(
                        news
                    )
                }
            } catch (e: Exception) {

                withContext(Dispatchers.Main) {
                    _uiState.value = NewsUiState.Failure(
                        news, "There was an issue fetching the latest news! Please try again."
                    )
                }
            }
        }
    }

    sealed class NewsUiState {

        class Loading(var isLoading: Boolean = true) : NewsUiState()
        class Success(val data: List<Article>) : NewsUiState()
        class Failure(val data: List<Article>, val message: String) : NewsUiState()

    }
}

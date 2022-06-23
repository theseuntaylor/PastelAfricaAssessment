package com.theseuntaylor.pastelafricaassessment.ui.utils

import com.theseuntaylor.pastelafricaassessment.data.model.NewsResponse

sealed class HomeUiState {

    object Loading : HomeUiState()
    class Success(val data: NewsResponse) : HomeUiState()
    class Failure(val message: String) : HomeUiState()

}
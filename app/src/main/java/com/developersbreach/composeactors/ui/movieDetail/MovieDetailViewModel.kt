package com.developersbreach.composeactors.ui.movieDetail

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.developersbreach.composeactors.model.MovieDetail
import com.developersbreach.composeactors.repository.AppRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class MovieDetailViewModel(
    application: Application,
    private val movieId: Int,
    private val repository: AppRepository
) : AndroidViewModel(application) {

    // Holds the state for values in DetailsViewState
    var uiState by mutableStateOf(MovieDetailViewState(movieData = null))
        private set

    init {
        viewModelScope.launch {
            try {
                startFetchingDetails()
            } catch (e: IOException) {
                Timber.e("$e")
            }
        }
    }

    // Update the values in uiState from all data sources.
    private suspend fun startFetchingDetails() {
        val movieData = repository.getSelectedMovieData(movieId)
        uiState = MovieDetailViewState(
            movieData = movieData
        )
    }

    companion object {

        /**
         * Factory for [MovieDetailViewModel] to provide with [AppRepository]
         */
        fun provideFactory(
            application: Application,
            movieId: Int,
            repository: AppRepository
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                        return MovieDetailViewModel(application, movieId, repository) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for MovieDetailViewModel class")
                }
            }
        }
    }
}

/**
 * Models the UI state for the [MovieDetailScreen] screen.
 */
data class MovieDetailViewState(
    val movieData: MovieDetail?
)
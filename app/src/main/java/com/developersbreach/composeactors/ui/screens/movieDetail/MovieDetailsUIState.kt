package com.developersbreach.composeactors.ui.screens.movieDetail

import com.developersbreach.composeactors.data.model.ActorDetail
import com.developersbreach.composeactors.data.model.Cast
import com.developersbreach.composeactors.data.model.Movie
import com.developersbreach.composeactors.data.model.MovieDetail

// TODO - create a sealed class to contains the different states

/**
 * Models the UI state for the [MovieDetailScreen] screen.
 */
data class MovieDetailsUIState(
    val movieData: MovieDetail?,
    val similarMovies: List<Movie> = emptyList(),
    val recommendedMovies: List<Movie> = emptyList(),
    val movieCast: List<Cast> = emptyList(),
    val isFetchingDetails: Boolean = false,
)

/**
 * Models the UI state for the SheetContentActorDetails modal sheet.
 */
data class ActorsSheetUIState(
    val selectedActorDetails: ActorDetail? = null
)
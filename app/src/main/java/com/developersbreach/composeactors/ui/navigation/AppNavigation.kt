package com.developersbreach.composeactors.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.developersbreach.composeactors.ui.screens.actorDetails.ActorDetailsViewModel
import com.developersbreach.composeactors.ui.screens.actorDetails.ActorDetailsScreen
import com.developersbreach.composeactors.ui.screens.home.HomeScreen
import com.developersbreach.composeactors.ui.screens.home.HomeViewModel
import com.developersbreach.composeactors.ui.screens.movieDetail.MovieDetailScreen
import com.developersbreach.composeactors.ui.screens.movieDetail.MovieDetailViewModel
import com.developersbreach.composeactors.ui.screens.search.SearchScreen
import com.developersbreach.composeactors.ui.screens.search.SearchViewModel


/**
 * @param startDestination default screen visible when user opens app.
 * @param routes gives access to all destination routes in [AppDestinations] object.
 *
 * This is an entry point triggered once activity starts.
 */
@Composable
fun AppNavigation(
    startDestination: String = AppDestinations.HOME_ROUTE,
    routes: AppDestinations = AppDestinations
) {
    // Create a NavHostController to handle navigation.
    val navController = rememberNavController()
    val actions = remember(navController) {
        AppActions(navController, routes)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        /**
         * Start destination.
         * Can later navigate to [ActorDetailsScreen] and [SearchScreen]
         * Has it's own viewModel [HomeViewModel] with factory & repository instance.
         */
        composable(
            AppDestinations.HOME_ROUTE
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                selectedActor = actions.selectedActor,
                navigateToSearch = actions.navigateToSearch,
                selectedMovie = actions.selectedMovie,
                viewModel = viewModel
            )
        }

        /**
         * Can later navigate to [ActorDetailsScreen]
         * Navigates back to previous screen with [AppActions.navigateUp]
         * Has it's own viewModel [SearchViewModel] with factory & repository instance.
         */
        composable(
            AppDestinations.SEARCH_ROUTE
        ) {
            val viewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                selectedActor = actions.selectedActor,
                navigateUp = actions.navigateUp,
                viewModel = viewModel
            )
        }

        /**
         * Two destinations ([HomeScreen] [SearchScreen]) can navigate to this screen.
         * Navigates back to previous screen with [AppActions.navigateUp]
         * Has it's own viewModel [ActorDetailsViewModel] with factory & repository instance.
         *
         * [AppDestinations.ACTOR_DETAIL_ID_KEY] contains id for the selected item in list.
         */
        composable(
            route = "${AppDestinations.ACTOR_DETAIL_ROUTE}/{${routes.ACTOR_DETAIL_ID_KEY}}",
            arguments = listOf(
                navArgument(routes.ACTOR_DETAIL_ID_KEY) { type = NavType.IntType }
            )
        ) {
            val viewModel = hiltViewModel<ActorDetailsViewModel>()
            ActorDetailsScreen(
                selectedMovie = actions.selectedMovie,
                navigateUp = actions.navigateUp,
                viewModel = viewModel
            )
        }

        /**
         * Two destinations ([HomeScreen] [ActorDetailsScreen]) can navigate to this screen.
         * Navigates back to previous screen with [AppActions.navigateUp]
         * Has it's own viewModel [MovieDetailViewModel] with factory & repository instance.
         *
         * [AppDestinations.MOVIE_DETAILS_ID_KEY] contains id for the selected item in list.
         */
        composable(
            route = "${AppDestinations.MOVIE_DETAILS_ROUTE}/{${routes.MOVIE_DETAILS_ID_KEY}}",
            arguments = listOf(
                navArgument(routes.MOVIE_DETAILS_ID_KEY) { type = NavType.IntType }
            )
        ) {
            val viewModel = hiltViewModel<MovieDetailViewModel>()
            MovieDetailScreen(
                navigateUp = actions.navigateUp,
                viewModel = viewModel,
                selectedMovie = actions.selectedMovie
            )
        }
    }
}
package com.abdullah.moviereviewapp.feature.navigation

import androidx.annotation.IdRes
import com.abdullah.moviereviewapp.R
import com.abdullah.moviereviewapp.base.navigation.BaseRouteDestination
import com.abdullah.moviereviewapp.base.navigation.BaseRouteNavHost

// For NavHosts on activities
sealed class RouteNavHost(@IdRes graphId: Int) : BaseRouteNavHost(graphId) {
    object MainNavigationHost : RouteNavHost(R.id.main_nav_graph)
}

// For Fragments to navigate
sealed class MoviePagesDestination(@IdRes destination: Int) : BaseRouteDestination(destination) {
    object MovieListPage : MoviePagesDestination(R.id.movieListFragment)
    object MovieDetailPage : MoviePagesDestination(R.id.movieDetailFragment)
}
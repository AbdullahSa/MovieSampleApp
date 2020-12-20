package com.abdullah.moviereviewapp.base.navigation

import androidx.annotation.IdRes

open class BaseRouteNavHost(@IdRes val graph: Int)
open class BaseRouteDestination(@IdRes val destination: Int) {
    object Back : BaseRouteDestination(-1)
}
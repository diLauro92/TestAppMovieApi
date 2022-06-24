package com.dilauro.testmovie.di

import com.dilauro.testmovie.model.MovieListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiServiceModule::class])
interface ApiServiceComponent {
    fun inject(movieListViewModel: MovieListViewModel)
}
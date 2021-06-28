package com.example.tangoapp.di

import com.example.tangoapp.TangoApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(value = [SingletonComponent::class])
object AppModule {

    @Provides
    fun provideApp(app: TangoApp): TangoApp = app
}
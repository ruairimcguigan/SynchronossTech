package com.aquidigital.synchronosstech.inject

import android.app.Application
import android.content.Context
import com.aquidigital.synchronosstech.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module

@Module(includes = [
    ViewModelModule::class
])
abstract class AppModule {
    @Binds
    abstract fun bindContext(app: Application): Context
}
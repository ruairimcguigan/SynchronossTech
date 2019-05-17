package com.aquidigital.synchronosstech.inject

import android.app.Application
import com.aquidigital.synchronosstech.App
import com.aquidigital.synchronosstech.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module

@Module(includes = [
    ViewModelModule::class
])
abstract class AppModule {

    @Binds
    internal abstract fun bindContext(application: App): Application
}
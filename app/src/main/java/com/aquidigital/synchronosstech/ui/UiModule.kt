package com.aquidigital.synchronosstech.ui

import android.app.Application
import android.content.res.Resources
import com.aquidigital.synchronosstech.ui.util.UiFormatter
import dagger.Module
import dagger.Provides

@Module
internal object UiModule {

    @JvmStatic
    @Provides
    fun bindUiFormatter(): UiFormatter {
        return UiFormatter()
    }

    @JvmStatic
    @Provides
    fun bindResource(app:Application): Resources {
        return app.resources
    }
}
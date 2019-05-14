package com.aquidigital.synchronosstech.syncworker

import androidx.work.Worker
import dagger.MapKey
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class SyncWorkerKey(val value: KClass<out Worker>)
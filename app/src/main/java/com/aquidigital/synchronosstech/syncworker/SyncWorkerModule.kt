package com.aquidigital.synchronosstech.syncworker

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SyncWorkerModule {

    @Binds
    @IntoMap
    @SyncWorkerKey(SyncSchedulerWorker::class)
    internal abstract fun bindMyWorkerFactory(worker: SyncSchedulerWorker.Factory): ChildWorkerFactory
}
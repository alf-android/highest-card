package com.alagunas.data.di

import com.alagunas.data.datamanager.db.core.buildDataBase
import com.alagunas.data.datamanager.db.core.provideDao
import com.alagunas.data.datasource.local.GameLocalDatasource
import com.alagunas.data.datasource.local.GameLocalDatasourceImpl
import com.alagunas.data.repositories.GameRepository
import com.alagunas.data.repositories.GameRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { buildDataBase(androidApplication()) }
    single { provideDao(get()) }

    single<GameLocalDatasource> { GameLocalDatasourceImpl(get()) }
    single<GameRepository> { GameRepositoryImpl(get()) }
}
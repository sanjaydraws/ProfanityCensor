package org.sanjaydraws.profanityfilter.di

import networking.createKtorHttpClient
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.sanjaydraws.profanityfilter.censoredText.data.network.CensoredDataSource
import org.sanjaydraws.profanityfilter.censoredText.presentation.censor_filter.CensorTextViewModel
import org.sanjaydraws.profanityfilter.censoredText.data.network.CensoredRemoteDataSource


/**
 * Created by Sanjay Prajapat on 14/03/2022 11:54 PM
 * Copyright (c) 2022 . All rights reserved.
 *
 * */

/**
 * provide service module
 * */
expect val platformModule: Module

val sharedModule = module {
    single { createKtorHttpClient(get()) }
    singleOf(::CensoredRemoteDataSource).bind<CensoredDataSource>()

//    single { MyRepositoryImpl(get()) } bind MyRepository::class
//    singleOf(::MyRepositoryImpl).bind<MyRepository>()

    viewModel { CensorTextViewModel(get()) }
//    viewModel { MyViewModel(get()) } // Pass dependencies explicitly if required
}

//
//val androidHttpClientModule = module {
//    single<HttpClientEngine> { Android.create() } // Ktor Android engine
//}

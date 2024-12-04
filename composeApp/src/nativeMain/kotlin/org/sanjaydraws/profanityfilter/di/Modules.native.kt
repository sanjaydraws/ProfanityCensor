package org.sanjaydraws.profanityfilter.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module
import org.koin.core.module.Module


actual val platformModule: Module
    get() = module {
        single{
            DbClient()
        }
        single<HttpClientEngine> { Darwin.create() }
    }
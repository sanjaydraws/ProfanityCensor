package org.sanjaydraws.profanityfilter.di



import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module


actual val platformModule: Module
    get() = module {
        single { DbClient() }
        single<HttpClientEngine> { OkHttp.create() }
    }



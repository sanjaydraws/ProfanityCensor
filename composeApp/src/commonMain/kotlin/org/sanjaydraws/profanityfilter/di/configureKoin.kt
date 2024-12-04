package org.sanjaydraws.profanityfilter.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun configureKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule)
    }
}
package org.sanjaydraws.profanityfilter.di

import org.koin.dsl.module

actual val platformModule = module {
    single{ DbClient() }
}
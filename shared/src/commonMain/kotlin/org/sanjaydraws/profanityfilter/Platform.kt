package org.sanjaydraws.profanityfilter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
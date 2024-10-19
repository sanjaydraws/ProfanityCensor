package org.sanjaydraws.profanityfilter


data class ProfanityResponse(
    val original: String,
    val censored: String,
    val has_profanity: Boolean
)

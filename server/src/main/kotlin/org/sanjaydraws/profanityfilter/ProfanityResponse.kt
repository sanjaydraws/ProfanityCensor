package org.sanjaydraws.profanityfilter


data class ProfanityResponse(
    val original: String,
    val censored: String,
    val hasProfanity: Boolean,
    val statistics: ProfanityStatistics

)

data class ProfanityStatistics(
    val totalProfaneWords:Int,
    val profaneWordsCount:Map<String, Int>
)

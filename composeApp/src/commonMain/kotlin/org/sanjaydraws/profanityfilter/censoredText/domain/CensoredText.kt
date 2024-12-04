package networking

import kotlinx.serialization.Serializable

//@Serializable
//data class CensoredText(
//    val result: String
//)

@kotlinx.serialization.Serializable
data class CensoredText(
    val original: String,
    val censored: String,
    val hasProfanity: Boolean,
    val statistics: Statistics
)


@kotlinx.serialization.Serializable
data class CensorRequest(
    val text: String,
    val replacement: String
)

@Serializable
data class ProfanityData(
    val original: String?,
    val censored: String?,
    val hasProfanity: Boolean?,
    val statistics: Statistics?
)

@Serializable
data class Statistics(
    val totalProfaneWords: Int?,
    val profaneWordsCount: Map<String, Int>?
)

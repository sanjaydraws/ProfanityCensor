package org.sanjaydraws.profanityfilter.censoredText.data.network

import networking.CensoredText
import org.sanjaydraws.profanityfilter.core.domain.ResultResponse


interface CensoredDataSource {
    suspend fun filterCensoredText(uncensored: String, replacement: String = "*"): ResultResponse<CensoredText>
}

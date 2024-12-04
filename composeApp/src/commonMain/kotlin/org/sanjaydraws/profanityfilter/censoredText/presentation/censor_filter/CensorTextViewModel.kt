package org.sanjaydraws.profanityfilter.censoredText.presentation.censor_filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import networking.CensoredText
import org.sanjaydraws.profanityfilter.censoredText.data.network.CensoredRemoteDataSource
import org.sanjaydraws.profanityfilter.core.domain.ResultResponse


class CensorTextViewModel(
    private val censoredRemoteDataSource: CensoredRemoteDataSource
) : ViewModel() {

    private val _censoredText = MutableStateFlow<ResultResponse<CensoredText>?>(null)
    val censoredText: StateFlow<ResultResponse<CensoredText>?> = _censoredText

    fun censorText(text: String, replacement: String = "*") {
        viewModelScope.launch {
            val result = censoredRemoteDataSource.filterCensoredText(text, replacement)
            _censoredText.value = result
        }
    }
    // Function to clear result when text is empty
    fun clearResult() {
        _censoredText.value = null
    }
}


package com.cringe.mobileip.server.model.needier.matching

import com.cringe.mobileip.server.model.utils.HelperData
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class MatchingNeederHelperAnswer(
    val helperResponses: List<HelperData>
)
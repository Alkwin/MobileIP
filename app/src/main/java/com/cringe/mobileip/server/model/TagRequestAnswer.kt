package com.cringe.mobileip.server.model

import com.cringe.mobileip.server.model.utils.tags.Tag
import kotlinx.serialization.Serializable

@Serializable
data class TagRequestAnswer(val tags: Map<Tag, String>) // we receive either 'service' or 'product' from the server, so we will have to convert it later on to TagType

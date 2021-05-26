package com.cringe.mobileip.server.model.utils

import com.cringe.mobileip.server.model.utils.tags.Tag

data class Order(
    val userName: String,
    val tags: List<Tag>,
    val details: String
)

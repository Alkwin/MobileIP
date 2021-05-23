package com.cringe.mobileip.server.model.utils.tags

interface TagTypeAndWeight {
    val type: TagType
    val weight: Any

    fun isSelected(): Boolean
}